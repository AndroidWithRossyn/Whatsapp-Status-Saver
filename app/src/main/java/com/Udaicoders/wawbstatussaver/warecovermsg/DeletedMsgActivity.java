package com.Udaicoders.wawbstatussaver.warecovermsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ironsource.mediationsdk.IronSource;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.warecovermsg.adapter.DeletedMsgAdapter;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgDatabaseClient;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgTable;

import java.util.List;

public class DeletedMsgActivity extends AppCompatActivity {

    private RelativeLayout loaderLay, emptyLay;
    private RecyclerView rvDeletedMsg;
    private SwipeRefreshLayout refreshLayout;
    private DeletedMsgAdapter adapter;
    private ImageView back;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted_msg);

        back = findViewById(R.id.backIV);
        back.setOnClickListener(v -> onBackPressed());

        loaderLay = findViewById(R.id.loaderLay);
        emptyLay = findViewById(R.id.emptyLay);

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> {
            //reload data from local database
            loadDeletedMsgFromLocalDatabase();
        });
        rvDeletedMsg = findViewById(R.id.rvDeletedMsg);

        loadDeletedMsgFromLocalDatabase();

        container = findViewById(R.id.banner_container);
        if (!AdController.isLoadIronSourceAd) {
            /*admob*/
            AdController.loadBannerAd(DeletedMsgActivity.this, container);
            AdController.loadInterAd(DeletedMsgActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AdController.isLoadIronSourceAd) {
            AdController.destroyIron();
            AdController.ironBanner(DeletedMsgActivity.this, container);
            // call the IronSource onResume method
            IronSource.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (AdController.isLoadIronSourceAd) {
            // call the IronSource onPause method
            IronSource.onPause(this);
        }
    }


    loadDataAsync async;

    public void loadDeletedMsgFromLocalDatabase() {
        async = new loadDataAsync();
        async.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (async != null) {
            async.cancel(true);
        }
    }


    class loadDataAsync extends AsyncTask<Void, Void, List<DeletedMsgTable>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loaderLay.setVisibility(View.VISIBLE);
            emptyLay.setVisibility(View.GONE);
            rvDeletedMsg.setVisibility(View.GONE);
        }

        @Override
        protected List<DeletedMsgTable> doInBackground(Void... voids) {
            List<DeletedMsgTable> records = DeletedMsgDatabaseClient.getInstance(DeletedMsgActivity.this).
                    getAppDatabase().daoDeletedMsgAccess().getDeletedRecord();
            return records;
        }

        @Override
        protected void onPostExecute(List<DeletedMsgTable> records) {
            super.onPostExecute(records);

            new Handler().postDelayed(() -> {
                if (records != null && records.size() != 0) {

                    if (adapter == null) {
                        adapter = new DeletedMsgAdapter(deletedMsgTable -> {
                            if (deletedMsgTable != null) {
                                Intent myapp = new Intent(DeletedMsgActivity.this, DeletedMsgByUsernameActivity.class);
                                myapp.putExtra("username", deletedMsgTable.getUsername());
                                startActivity(myapp);
                            }
                        });
                    }

                    if (rvDeletedMsg.getAdapter() == null) {
                        rvDeletedMsg.setAdapter(adapter);
                    }
                    if (adapter != null) {
                        adapter.doRefresh(records);
                    }
                    rvDeletedMsg.setVisibility(View.VISIBLE);
                } else {
                    rvDeletedMsg.setVisibility(View.GONE);
                }

                loaderLay.setVisibility(View.GONE);

                if (records == null || records.size() == 0) {
                    emptyLay.setVisibility(View.VISIBLE);
                } else {
                    emptyLay.setVisibility(View.GONE);
                }

                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }

            }, 1000);
        }
    }
}
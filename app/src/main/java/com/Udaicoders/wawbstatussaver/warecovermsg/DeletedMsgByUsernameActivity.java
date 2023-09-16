package com.Udaicoders.wawbstatussaver.warecovermsg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.ironsource.mediationsdk.IronSource;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.warecovermsg.adapter.DeletedMsgByUsernameAdapter;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgDatabaseClient;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgTable;

import java.util.ArrayList;
import java.util.List;

public class DeletedMsgByUsernameActivity extends AppCompatActivity {

    private ImageView back, bgIV;
    private TextView tvActivityTitle;
    private LinearLayout container;
    private RelativeLayout loaderLay, emptyLay;
    private RecyclerView rvDeletedMsgByUsername;
    private SwipeRefreshLayout refreshLayout;
    private DeletedMsgByUsernameAdapter adapter;
    private String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_deleted_msg_by_username);

        back = findViewById(R.id.backIV);
        back.setOnClickListener(v -> onBackPressed());

        bgIV = findViewById(R.id.bgIV);
        Glide.with(this).load(R.drawable.chat_bg).into(bgIV);

        tvActivityTitle = findViewById(R.id.tvActivityTitle);

        username = getIntent().getExtras().getString("username", null);
        if (!TextUtils.isEmpty(username)) {
            tvActivityTitle.setText(username);
        }

        loaderLay = findViewById(R.id.loaderLay);
        emptyLay = findViewById(R.id.emptyLay);

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //reload data from local database
                loadDeletedMsgFromLocalDatabase();
            }
        });
        rvDeletedMsgByUsername = findViewById(R.id.rvDeletedMsgByUsername);

        loadDeletedMsgFromLocalDatabase();

        container = findViewById(R.id.banner_container);
        if (!AdController.isLoadIronSourceAd) {
            /*admob*/
            AdController.loadBannerAd(DeletedMsgByUsernameActivity.this, container);
            AdController.loadInterAd(DeletedMsgByUsernameActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AdController.isLoadIronSourceAd) {
            AdController.destroyIron();
            AdController.ironBanner(DeletedMsgByUsernameActivity.this, container);
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
        }

        @Override
        protected List<DeletedMsgTable> doInBackground(Void... voids) {
            List<DeletedMsgTable> records = new ArrayList<DeletedMsgTable>();
            if (!TextUtils.isEmpty(username)) {
                records = DeletedMsgDatabaseClient.getInstance(DeletedMsgByUsernameActivity.this).getAppDatabase().daoDeletedMsgAccess().getAllDeletedRecordByUsername(username, true);
            }
            return records;
        }

        @Override
        protected void onPostExecute(List<DeletedMsgTable> records) {
            super.onPostExecute(records);

            new Handler().postDelayed(() -> {
                if (records != null && records.size() != 0) {
                    if (adapter == null) {
                        adapter = new DeletedMsgByUsernameAdapter();
                    }
                    if (rvDeletedMsgByUsername.getAdapter() == null) {
                        rvDeletedMsgByUsername.setAdapter(adapter);
                    }
                    if (adapter != null) {
                        adapter.doRefresh(records);
                    }
                    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeletedMsgByUsernameActivity.this);
                    linearLayoutManager.setReverseLayout(true);
                    rvDeletedMsgByUsername.setLayoutManager(linearLayoutManager);
                    rvDeletedMsgByUsername.setVisibility(View.VISIBLE);
                } else {
                    rvDeletedMsgByUsername.setVisibility(View.GONE);
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
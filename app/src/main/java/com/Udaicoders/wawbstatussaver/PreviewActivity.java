package com.Udaicoders.wawbstatussaver;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.viewpager.widget.ViewPager;


import com.ironsource.mediationsdk.IronSource;
import com.Udaicoders.wawbstatussaver.adapter.PreviewAdapter;
import com.Udaicoders.wawbstatussaver.model.StatusModel;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.util.SharedPrefs;
import com.Udaicoders.wawbstatussaver.util.Utils;


import java.io.File;
import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<StatusModel> imageList;
    int position;

    LinearLayout downloadIV, shareIV, deleteIV, wAppIV;
    PreviewAdapter previewAdapter;
    String statusDownload;
    ImageView backIV;
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        Utils.setLanguage(PreviewActivity.this, SharedPrefs.getLang(PreviewActivity.this));

        backIV = findViewById(R.id.backIV);
        viewPager = findViewById(R.id.viewPager);
        shareIV = findViewById(R.id.shareIV);
        downloadIV = findViewById(R.id.downloadIV);
        deleteIV = findViewById(R.id.deleteIV);
        wAppIV = findViewById(R.id.repostIV);

        imageList = getIntent().getParcelableArrayListExtra("images");
        position = getIntent().getIntExtra("position", 0);
        statusDownload = getIntent().getStringExtra("statusdownload");

        if (statusDownload.equals("download")) {
            downloadIV.setVisibility(View.GONE);
        } else {
            downloadIV.setVisibility(View.VISIBLE);
        }

        previewAdapter = new PreviewAdapter(PreviewActivity.this, imageList);
        viewPager.setAdapter(previewAdapter);
        viewPager.setCurrentItem(position);

        downloadIV.setOnClickListener(clickListener);
        shareIV.setOnClickListener(clickListener);
        deleteIV.setOnClickListener(clickListener);
        backIV.setOnClickListener(clickListener);
        wAppIV.setOnClickListener(clickListener);

        /*admob*/
        container = findViewById(R.id.banner_container);
        if (!AdController.isLoadIronSourceAd) {
            AdController.loadBannerAd(PreviewActivity.this, container);
            AdController.loadInterAd(PreviewActivity.this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (AdController.isLoadIronSourceAd) {
            AdController.destroyIron();
            AdController.ironBanner(PreviewActivity.this, container);
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

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backIV:
                    onBackPressed();
                    break;

                case R.id.downloadIV:
                    if (imageList.size() > 0) {
                        try {
                            Utils.download(PreviewActivity.this, imageList.get(viewPager.getCurrentItem()).getFilePath());
                            Toast.makeText(PreviewActivity.this, getResources().getString(R.string.saved_success), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(PreviewActivity.this, "Sorry we can't move file.try with other file.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        finish();
                    }
                    break;

                case R.id.shareIV:
                    if (imageList.size() > 0) {
                        Utils.shareFile(PreviewActivity.this, Utils.isVideoFile(PreviewActivity.this, imageList.get(viewPager.getCurrentItem()).getFilePath()),imageList.get(viewPager.getCurrentItem()).getFilePath());
                    } else {
                        finish();
                    }
                    break;

                case R.id.deleteIV:
                    if (imageList.size() > 0) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PreviewActivity.this);
                        alertDialog.setTitle(R.string.confirm);
                        alertDialog.setMessage(R.string.del_status);
                        alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                int currentItem = 0;

                                if (statusDownload.equals("download")) {
                                    File file = new File(imageList.get(viewPager.getCurrentItem()).getFilePath());
                                    if (file.exists()) {
                                        boolean del = file.delete();
                                        delete(currentItem);
                                    }
                                }else {
                                    DocumentFile fromTreeUri = DocumentFile.fromSingleUri(PreviewActivity.this, Uri.parse(imageList.get(viewPager.getCurrentItem()).getFilePath()));
                                    if (fromTreeUri.exists()) {
                                        boolean del = fromTreeUri.delete();
                                        delete(currentItem);
                                    }
                                }
                            }
                        });
                        alertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
                    } else {
                        finish();
                    }
                    break;

                case R.id.repostIV:

                    Utils.repostWhatsApp(PreviewActivity.this, Utils.isVideoFile(PreviewActivity.this, imageList.get(viewPager.getCurrentItem()).getFilePath()),imageList.get(viewPager.getCurrentItem()).getFilePath());

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    void delete(int currentItem){
        if (imageList.size() > 0 && viewPager.getCurrentItem() < imageList.size()) {
            currentItem = viewPager.getCurrentItem();
        }
        imageList.remove(viewPager.getCurrentItem());
        previewAdapter = new PreviewAdapter(PreviewActivity.this, imageList);
        viewPager.setAdapter(previewAdapter);

        Intent intent = new Intent();
        setResult(10, intent);

        if (imageList.size() > 0) {
            viewPager.setCurrentItem(currentItem);
        } else {
            finish();
        }

        //ads
        if (AdController.isLoadIronSourceAd){
            AdController.ironShowInterstitial(PreviewActivity.this, null, 0);
        }else {
            AdController.showInterAd(PreviewActivity.this, null, 0);
        }
    }

}

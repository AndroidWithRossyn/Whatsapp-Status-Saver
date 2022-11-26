package com.banrossyn.socialsaver;

import static com.banrossyn.socialsaver.util_items.Utils.shareImage;
import static com.banrossyn.socialsaver.util_items.Utils.shareImageVideoOnWhatsapp;
import static com.banrossyn.socialsaver.util_items.Utils.shareVideo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.banrossyn.socialsaver.adapters.ShowImagesAdapter;
import com.banrossyn.socialsaver.databinding.ActivityFullViewBinding;
import com.banrossyn.socialsaver.util_items.Utils;

import java.io.File;
import java.util.ArrayList;

public class FullViewActivity extends AppCompatActivity {
    private ActivityFullViewBinding binding;
    private FullViewActivity activity;
    private ArrayList<File> fileArrayList;
    private int Position = 0;
    ShowImagesAdapter showImagesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_view);
        activity = this;


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fileArrayList= (ArrayList<File>) getIntent().getSerializableExtra("ImageDataFile");
            Position = getIntent().getIntExtra("Position",0);
        }
        initViews();

    }

    public void initViews(){
        showImagesAdapter=new ShowImagesAdapter(this, fileArrayList,FullViewActivity.this);
        binding.vpView.setAdapter(showImagesAdapter);
        binding.vpView.setCurrentItem(Position);

        binding.vpView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                Position=arg0;
                System.out.println("Current position=="+Position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int num) {
            }
        });

        binding.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(activity);
                ab.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean b=fileArrayList.get(Position).delete();
                        if (b){
                            deleteFileAA(Position);
                        }
                    }
                });
                ab.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = ab.create();
                alert.setTitle(getResources().getString(R.string.do_u_want_to_dlt));
                alert.show();
            }
        });
        binding.imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileArrayList.get(Position).getName().contains(".mp4")){
                    Log.d("SSSSS", "onClick: "+fileArrayList.get(Position)+"");
                    shareVideo(activity,fileArrayList.get(Position).getPath());
                }else {
                    shareImage(activity,fileArrayList.get(Position).getPath());
                }
            }
        });
        binding.imWhatsappShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileArrayList.get(Position).getName().contains(".mp4")){
                    shareImageVideoOnWhatsapp(activity,fileArrayList.get(Position).getPath(),true);
                }else {
                    shareImageVideoOnWhatsapp(activity,fileArrayList.get(Position).getPath(),false);
                }
            }
        });
        binding.imClose.setOnClickListener(v -> {
            onBackPressed();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }
    public void deleteFileAA(int position){
        fileArrayList.remove(position);
        showImagesAdapter.notifyDataSetChanged();
        Utils.setToast(activity,getResources().getString(R.string.file_deleted));
        if (fileArrayList.size()==0){
            onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

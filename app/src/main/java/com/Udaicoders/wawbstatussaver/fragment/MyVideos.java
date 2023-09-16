package com.Udaicoders.wawbstatussaver.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.adapter.MyStatusAdapter;
import com.Udaicoders.wawbstatussaver.model.StatusModel;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.util.Utils;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyVideos extends Fragment implements MyStatusAdapter.OnCheckboxListener {

    ArrayList<StatusModel> fileList = null;
    GridView gridView;
    MyStatusAdapter adapter;
    int save = 10;
    ArrayList<StatusModel> filesToDelete = new ArrayList<>();
    LinearLayout actionLay, deleteIV;
    CheckBox selectAll;
    RelativeLayout loaderLay, emptyLay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_status_fragment, container, false);

        loaderLay = rootView.findViewById(R.id.loaderLay);
        emptyLay = rootView.findViewById(R.id.emptyLay);

        gridView = rootView.findViewById(R.id.videoGrid);
        populateVideo();

        actionLay = rootView.findViewById(R.id.actionLay);
        deleteIV = rootView.findViewById(R.id.deleteIV);
        deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!filesToDelete.isEmpty()) {
                    new AlertDialog.Builder(getContext())
                            .setMessage(getResources().getString(R.string.delete_alert))
                            .setCancelable(true)
                            .setNegativeButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    new deleteAll().execute();
                                }
                            })
                            .setPositiveButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                }
            }
        });

        selectAll = rootView.findViewById(R.id.selectAll);
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (!compoundButton.isPressed()) {
                    return;
                }

                filesToDelete.clear();

                for (int i = 0; i < fileList.size(); i++) {
                    if (!fileList.get(i).selected) {
                        b = true;
                        break;
                    }
                }

                if (b) {
                    for (int i = 0; i < fileList.size(); i++) {
                        fileList.get(i).selected = true;
                        filesToDelete.add(fileList.get(i));
                    }
                    selectAll.setChecked(true);
                } else {
                    for (int i = 0; i < fileList.size(); i++) {
                        fileList.get(i).selected = false;
                    }
                    actionLay.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    class deleteAll extends AsyncTask<Void, Void, Void>{
        int success = -1;
        AlertDialog alertDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = Utils.loadingPopup(getActivity());
            alertDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<StatusModel> deletedFiles = new ArrayList<>();

            for (StatusModel details : filesToDelete) {
                File file = new File(details.getFilePath());
                Log.e( "onClick: ", file.getAbsolutePath());
                if (file.exists()) {
                    if (file.delete()) {
                        deletedFiles.add(details);
                        if (success == 0) {
                            break;
                        }
                        success = 1;
                    } else {
                        success = 0;
                    }
                } else {
                    success = 0;
                }
            }

            filesToDelete.clear();
            for (StatusModel deletedFile : deletedFiles) {
                fileList.remove(deletedFile);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            adapter.notifyDataSetChanged();
            if (success == 0) {
                Toast.makeText(getContext(), getResources().getString(R.string.delete_error), Toast.LENGTH_SHORT).show();
            } else if (success == 1) {
                Toast.makeText(getActivity(), getResources().getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
            }
            actionLay.setVisibility(View.GONE);
            selectAll.setChecked(false);
            alertDialog.dismiss();
            if (AdController.isLoadIronSourceAd) {
                AdController.ironShowInterstitial(getActivity(), null, 0);
            } else {
                AdController.showInterAd(getActivity(), null, 0);
            }
        }
    }

    public void populateVideo() {
        new loadDataAsync().execute();
    }

    class loadDataAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loaderLay.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getFromSdcard();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            new Handler().postDelayed(() -> {
                if (fileList != null) {
                    adapter = new MyStatusAdapter(MyVideos.this, fileList, MyVideos.this);
                    gridView.setAdapter(adapter);
                }
                loaderLay.setVisibility(View.GONE);

                if (fileList == null || fileList.size() == 0) {
                    emptyLay.setVisibility(View.VISIBLE);
                } else {
                    emptyLay.setVisibility(View.GONE);
                }
            }, 1000);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.onActivityResult(requestCode, resultCode, data);
        if (requestCode == save && resultCode == save) {
            adapter.notifyDataSetChanged();

            getFromSdcard();
            if (fileList != null) {
                adapter = new MyStatusAdapter(MyVideos.this, fileList, MyVideos.this);
                gridView.setAdapter(adapter);
            }

            actionLay.setVisibility(View.GONE);
            selectAll.setChecked(false);
        }
    }

    public void getFromSdcard() {
        File videoFiles = new File(Environment
                .getExternalStorageDirectory().toString() + File.separator + "Download" + File.separator + getResources().getString(R.string.app_name) + "/Videos");

        if (videoFiles.isDirectory()) {
            fileList = new ArrayList<>();
            if (videoFiles.isDirectory()) {
                File[] listFile = videoFiles.listFiles();
                Arrays.sort(listFile, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                for (int i = 0; i < listFile.length; i++) {

                    fileList.add(new StatusModel(listFile[i].getAbsolutePath()));

                }
            }
        }

    }

    @Override
    public void onCheckboxListener(View view, List<StatusModel> list) {
        filesToDelete.clear();
        for (StatusModel details : list) {
            if (details.isSelected()) {
                filesToDelete.add(details);
            }
        }
        if (filesToDelete.size() == fileList.size()) {
            selectAll.setChecked(true);
        }
        if (!filesToDelete.isEmpty()) {
            actionLay.setVisibility(View.VISIBLE);
            return;
        }
        selectAll.setChecked(false);
        actionLay.setVisibility(View.GONE);
    }
}

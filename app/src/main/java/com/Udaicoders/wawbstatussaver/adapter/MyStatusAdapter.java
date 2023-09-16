package com.Udaicoders.wawbstatussaver.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.Udaicoders.wawbstatussaver.PreviewActivity;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.model.StatusModel;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MyStatusAdapter extends BaseAdapter {

    Fragment context;
    List<StatusModel> arrayList;
    int width;
    LayoutInflater inflater;
    public OnCheckboxListener onCheckboxListener;

    public MyStatusAdapter(Fragment context, List<StatusModel> arrayList, OnCheckboxListener onCheckboxListener) {
        this.context = context;
        this.arrayList = arrayList;

        inflater = (LayoutInflater) context.getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DisplayMetrics displayMetrics = context.getResources()
                .getDisplayMetrics();
        width = displayMetrics.widthPixels; // width of the device

        this.onCheckboxListener = onCheckboxListener;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {

        View grid = inflater.inflate(R.layout.row_my_status, null);

        ImageView play = grid.findViewById(R.id.play);

        if (isVideoFile(arrayList.get(arg0).getFilePath())) {
            play.setVisibility(View.VISIBLE);
        } else {
            play.setVisibility(View.GONE);
        }


        grid.setLayoutParams(new GridView.LayoutParams((width * 320 / 1080),
                (width * 320 / 1080)));
        ImageView imageView = (ImageView) grid
                .findViewById(R.id.gridImageVideo);


        Glide.with(context.getActivity()).load(arrayList.get(arg0).getFilePath()).into(imageView);


        CheckBox checkbox = grid.findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((StatusModel) arrayList.get(arg0)).setSelected(isChecked);
                if (onCheckboxListener != null) {
                    onCheckboxListener.onCheckboxListener(buttonView, arrayList);
                }
            }
        });
        if (arrayList.get(arg0).isSelected()) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "click");
                Intent intent = new Intent(context.getActivity(), PreviewActivity.class);
                intent.putParcelableArrayListExtra("images", (ArrayList<? extends Parcelable>) arrayList);
                intent.putExtra("position", arg0);
                intent.putExtra("statusdownload", "download");
				context.startActivityForResult(intent, 10);

            }
        });

        return grid;
    }


    public interface OnCheckboxListener {
        void onCheckboxListener(View view, List<StatusModel> list);
    }

    public boolean isVideoFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("video");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
    }
}

package com.Udaicoders.wawbstatussaver.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.VideoPlayerActivity;
import com.Udaicoders.wawbstatussaver.model.StatusModel;
import com.Udaicoders.wawbstatussaver.util.Utils;

import java.util.ArrayList;

public class PreviewAdapter extends PagerAdapter {
    Activity activity;
    ArrayList<StatusModel> imageList;


    public PreviewAdapter(Activity activity, ArrayList<StatusModel> imageList) {
        this.activity = activity;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.preview_list_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        ImageView iconplayer = itemView.findViewById(R.id.iconplayer);

        if (!Utils.getBack(imageList.get(position).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
            iconplayer.setVisibility(View.VISIBLE);
        } else {
            iconplayer.setVisibility(View.GONE);
        }

        Glide.with(this.activity).load(imageList.get(position).getFilePath()).into(imageView);

        imageView.setOnClickListener(view -> {
            if (!Utils.getBack(imageList.get(position).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
                Utils.mPath = imageList.get(position).getFilePath();
                activity.startActivity(new Intent(activity, VideoPlayerActivity.class));
            }
        });
        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }
}

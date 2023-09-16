package com.Udaicoders.wawbstatussaver.font.adapter;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udaicoders.wawbstatussaver.R;

import java.util.ArrayList;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.MyViewHolder> {
    private ArrayList<String> fontItems;
    private Activity activity;

    @Override
    @NonNull
    public EmojiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_font, parent, false);
        return new MyViewHolder(row);
    }

    public EmojiAdapter(ArrayList<String> fontItems, Activity activity) {
        this.fontItems = fontItems;
        this.activity = activity;

    }

    @Override
    public void onBindViewHolder(@NonNull final EmojiAdapter.MyViewHolder holder, final int position) {
        final String f = fontItems.get(position);

        holder.description.setText(f);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                String desStr = holder.description.getText().toString();
                Toast.makeText(activity, "Copied to clipboard! Your copied text is " + desStr, Toast.LENGTH_SHORT).show();
                ClipData clip = ClipData.newPlainText("simple text", desStr);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return fontItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title;
        TextView description;
        LinearLayout cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
//            title = itemView.findViewById(R.id.titleTV);
            description = itemView.findViewById(R.id.descriptionTV);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}



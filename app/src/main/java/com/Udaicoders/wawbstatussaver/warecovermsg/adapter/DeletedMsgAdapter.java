package com.Udaicoders.wawbstatussaver.warecovermsg.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgTable;

import java.util.List;


public class DeletedMsgAdapter extends RecyclerView.Adapter<DeletedMsgAdapter.UserViewHolder> {

    //list data
    private List<DeletedMsgTable> listData;
    private OnItemClickListener listener;

    public DeletedMsgAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * refresh and reload adapter data
     *
     * @param listData
     */
    public void doRefresh(List<DeletedMsgTable> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_deleted_msg, parent, false);
        UserViewHolder vh = new UserViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        DeletedMsgTable dataObject = getItem(position);

        holder.tvUserName.setText(!TextUtils.isEmpty(dataObject.getUsername()) ? dataObject.getUsername() : "");
        holder.tvLastMessage.setText(!TextUtils.isEmpty(dataObject.getMessage()) ? dataObject.getMessage() : "");
        holder.tvMessageTime.setText(!TextUtils.isEmpty(dataObject.getCreated_at()) ? dataObject.getCreated_at() : "");

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.OnItemClick(dataObject);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    /**
     * get single item data from list by position
     *
     * @param position
     * @return
     */
    private DeletedMsgTable getItem(int position) {
        return listData.get(position);
    }

    /**
     * user view holder
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvLastMessage, tvMessageTime;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
            tvMessageTime = itemView.findViewById(R.id.tvMessageTime);
        }
    }


    public interface OnItemClickListener {
        void OnItemClick(DeletedMsgTable deletedMsgTable);
    }

}

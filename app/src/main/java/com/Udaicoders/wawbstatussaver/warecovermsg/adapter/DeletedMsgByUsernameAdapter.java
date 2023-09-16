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


public class DeletedMsgByUsernameAdapter extends RecyclerView.Adapter<DeletedMsgByUsernameAdapter.UserViewHolder> {

    //list data
    private List<DeletedMsgTable> listData;

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

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_deleted_msg_by_username, parent, false);
        UserViewHolder vh = new UserViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        DeletedMsgTable dataObject = getItem(position);

        holder.tvMessage.setText(!TextUtils.isEmpty(dataObject.getMessage()) ? dataObject.getMessage() : "");
        holder.tvMessageTime.setText(!TextUtils.isEmpty(dataObject.getCreated_at()) ? dataObject.getCreated_at() : "");

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

        TextView tvMessage, tvMessageTime;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvMessageTime = itemView.findViewById(R.id.tvMessageTime);
        }
    }
}

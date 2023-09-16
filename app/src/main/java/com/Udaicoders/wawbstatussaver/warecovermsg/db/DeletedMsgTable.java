package com.Udaicoders.wawbstatussaver.warecovermsg.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class DeletedMsgTable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String username;
    private String message;
    private String created_at;
    private String deleted_at;
    private boolean is_deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Ignore
    protected DeletedMsgTable(Parcel in) {
        id = in.readLong();
        username = in.readString();
        message = in.readString();
        created_at = in.readString();
        deleted_at = in.readString();
        is_deleted = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeString(message);
        dest.writeString(created_at);
        dest.writeString(deleted_at);
        dest.writeByte((byte) (is_deleted ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Creator<DeletedMsgTable> CREATOR = new Creator<DeletedMsgTable>() {
        @Override
        public DeletedMsgTable createFromParcel(Parcel in) {
            return new DeletedMsgTable(in);
        }

        @Override
        public DeletedMsgTable[] newArray(int size) {
            return new DeletedMsgTable[size];
        }
    };

    public DeletedMsgTable(String username, String message, String created_at, String deleted_at, boolean is_deleted) {
        this.username = username;
        this.message = message;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.is_deleted = is_deleted;
    }

}

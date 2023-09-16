package com.Udaicoders.wawbstatussaver.warecovermsg.db;

import android.content.Context;

import androidx.room.Room;

public class DeletedMsgDatabaseClient {

    private DeletedMsgDatabase appDatabase;
    private static DeletedMsgDatabaseClient mInstance;

    private DeletedMsgDatabaseClient(Context mContext) {
        appDatabase = Room.databaseBuilder(mContext, DeletedMsgDatabase.class, "DeletedMsgDb")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DeletedMsgDatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DeletedMsgDatabaseClient(mCtx);
        }
        return mInstance;
    }


    public DeletedMsgDatabase getAppDatabase() {
        return appDatabase;
    }
}
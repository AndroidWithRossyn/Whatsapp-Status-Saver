package com.Udaicoders.wawbstatussaver.warecovermsg.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DeletedMsgTable.class}, version = 1, exportSchema = false)
public abstract class DeletedMsgDatabase extends RoomDatabase {
    public abstract DaoDeletedMsg daoDeletedMsgAccess();
}
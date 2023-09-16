package com.Udaicoders.wawbstatussaver.warecovermsg.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoDeletedMsg {
    @Insert
    long insertRecord(DeletedMsgTable object);

    @Insert
    void insertRecord(DeletedMsgTable... list);

    @Insert
    void insertRecord(List<DeletedMsgTable> list);

    @Query("SELECT * FROM DeletedMsgTable ORDER BY id ASC")
    List<DeletedMsgTable> getRecord();


    //    @Query("SELECT * FROM DeletedMsgTable  WHERE is_deleted = :isDeleted GROUP BY username ORDER BY id DESC")
    @Query("SELECT * FROM deletedmsgtable GROUP BY username ORDER BY MAX(created_at) DESC")
    List<DeletedMsgTable> getDeletedRecord();

    @Query("SELECT * FROM DeletedMsgTable WHERE id =:id")
    DeletedMsgTable getRecordById(long id);

    @Query("SELECT * FROM DeletedMsgTable WHERE username =:username AND is_deleted = :is_deleted ORDER BY id DESC LIMIT 1")
    DeletedMsgTable getLastNotDeletedRecordByUsername(String username, Boolean is_deleted);

    @Query("SELECT * FROM DeletedMsgTable WHERE username =:username AND is_deleted = :is_deleted ORDER BY id DESC")
    List<DeletedMsgTable> getAllDeletedRecordByUsername(String username, Boolean is_deleted);

    @Update
    int updateRecord(DeletedMsgTable object);

    @Delete
    void deleteRecord(DeletedMsgTable object);

    @Query("DELETE FROM DeletedMsgTable")
    void deleteRecordAll();

    @Query("DELETE FROM DeletedMsgTable WHERE id = :id")
    void deleteRecordById(int id);

    @Delete
    void deleteRecordAll(List<DeletedMsgTable> objects);
}

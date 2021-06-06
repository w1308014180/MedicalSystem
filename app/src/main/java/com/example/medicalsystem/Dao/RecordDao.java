package com.example.medicalsystem.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medicalsystem.Bean.Record;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert
    void insertRecord(Record... records);

    @Update
    void updateRecord(Record... records);

    @Delete
    void deleteRecord(Record... records);

    @Query("DELETE FROM RECORD")
    void deleteAllRecords();


    @Query("SELECT * FROM record ORDER BY recordId DESC")
    LiveData<List<Record>> getAllRecordLive();

}

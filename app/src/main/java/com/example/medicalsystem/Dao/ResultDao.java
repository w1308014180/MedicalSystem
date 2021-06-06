package com.example.medicalsystem.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medicalsystem.Bean.Result;

import java.util.List;

@Dao
public interface ResultDao {
    @Insert
    void insertResult(Result... results);

    @Update
    void updateResult(Result... results);

    @Delete
    void deleteResult(Result... results);

    @Query("DELETE FROM Result")
    void deleteAllResults();


    @Query("SELECT * FROM result ORDER BY resultId DESC")
    LiveData<List<Result>> getAllResultLive();
}

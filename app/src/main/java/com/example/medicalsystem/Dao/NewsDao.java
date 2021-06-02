package com.example.medicalsystem.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medicalsystem.Bean.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insertNews(News... news);

    @Update
    void updateNews(News... news);

    @Delete
    void deleteNews(News... news);


    @Query("SELECT * FROM NEWS ORDER BY ID DESC")
    LiveData<List<News>>getAllNewsLive();

}

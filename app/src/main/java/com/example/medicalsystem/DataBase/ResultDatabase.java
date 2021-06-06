package com.example.medicalsystem.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medicalsystem.Bean.Result;
import com.example.medicalsystem.Dao.ResultDao;


@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class ResultDatabase extends RoomDatabase {
    private static ResultDatabase INSTANCE;

    public static synchronized ResultDatabase getResultDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ResultDatabase.class,"result_database")
                    //.fallbackToDestructiveMigration() 破坏性迁移
                    .build();
        }
        return INSTANCE;
    }

    public abstract ResultDao getResultDao();

}

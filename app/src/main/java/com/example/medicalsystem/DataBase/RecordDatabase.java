package com.example.medicalsystem.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.Dao.NewsDao;
import com.example.medicalsystem.Dao.RecordDao;

//singleton
@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {
    private static RecordDatabase INSTANCE;

    public static synchronized RecordDatabase getRecordDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecordDatabase.class,"record_database")
                    //.fallbackToDestructiveMigration() 破坏性迁移
                    .build();
        }
        return INSTANCE;
    }

    public abstract RecordDao getRecordDao();

}

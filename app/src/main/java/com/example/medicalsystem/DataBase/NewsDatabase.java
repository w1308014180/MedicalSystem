package com.example.medicalsystem.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Dao.NewsDao;

//singleton
@Database(entities = {News.class}, version = 2, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    private static NewsDatabase INSTANCE;


    public static synchronized NewsDatabase getNewsDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class,"news_database")
                    //.fallbackToDestructiveMigration() 破坏性迁移
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return INSTANCE;
    }

    public abstract NewsDao getNewsDao();

    //数据库版本迁移
    static final Migration MIGRATION_2_3 = new Migration(2,3){

        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE news ADD COLUMN news_content STRING ");
            //若想删除某一列 则需重新建表 再删除原表
            /*database.execSQL("CREATE TABLE news_temp (id INTEGER PRIMARY KEY NOT NULL, news_title STRING,"+ "news_publicTime STRING)" );
            database.execSQL("INSERT INTO news_temp (id, news_title, news_public_time)"+"SELECT id, news_title, news_public_time FROM news");
            database.execSQL("DROP TABLE news");
            database.execSQL("ALTER TABLE news_temp RENAME to news");*/
        }
    };
}

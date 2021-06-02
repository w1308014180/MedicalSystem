package com.example.medicalsystem.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.medicalsystem.Bean.User;
import com.example.medicalsystem.DatabaseHelper.UserDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDatabase {
    public static final String DB_NAME = "user";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static UserDatabase userdb;

    private SQLiteDatabase db;

    private UserDatabase(Context context) {
        UserDatabaseHelper dbHelper = new UserDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static UserDatabase getInstance(Context context) {
        if (userdb == null) {
            userdb = new UserDatabase(context);
        }
        return userdb;
    }

    /**
     * 将User实例存储到数据库。
     */
    public int  saveUser(User user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?) ", new String[]{user.getUsername().toString(), user.getUserpwd().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor

                        .getColumnIndex("userpwd")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Query(String pwd,String name)
    {


        HashMap<String,String> hashmap=new HashMap<String,String>();
        Cursor cursor =db.rawQuery("select * from User where username=?", new String[]{name});

        // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from User where userpwd=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }


    }


}

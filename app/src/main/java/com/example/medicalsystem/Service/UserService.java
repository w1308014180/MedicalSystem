package com.example.medicalsystem.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.medicalsystem.Bean.User;
import com.example.medicalsystem.DatabaseHelper.UserDatabaseHelper;

public class UserService {
    private UserDatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper=new UserDatabaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,userpwd) values(?,?)";
        Object obj[]={user.getUsername(),user.getUserpwd()};
        sdb.execSQL(sql, obj);
        return true;
    }

}

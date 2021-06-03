package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicalsystem.Util.ImageUtil;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvNickName,tvAccount,tvAge,tvGender,tvCity,tvHome,tvSchool,tvBirthdayTime;
    private ImageView toEdit,ivAvatar,toBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profilectivity);
        initView();
        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }


    private void initData() {
        getDataFromSpf();
    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String account = spfRecord.getString("account", "");
        String nickName = spfRecord.getString("nick_name", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String birthDayTime = spfRecord.getString("birth_day_time", "");

        String image64 = spfRecord.getString("image_64", "");

        String age = getAgeByBirthDay(birthDayTime);

        tvAccount.setText(account);
        tvNickName.setText(nickName);
        tvAge.setText(age);
        tvHome.setText(city);
        tvSchool.setText(school);
        tvBirthdayTime.setText(birthDayTime);
        tvGender.setText(gender);
        tvCity.setText(city);

        ivAvatar.setImageBitmap(ImageUtil.base64ToImage(image64));

    }

    private String getAgeByBirthDay(String birthDayTime) {
        // 2000年11月1日12时34分
        if (TextUtils.isEmpty(birthDayTime)) {
            return "";
        }

        try {
            int index = birthDayTime.indexOf("年");
            String result = birthDayTime.substring(0, index);

            int parseInt = Integer.parseInt(result);
            return String.valueOf(2021 - parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }


    private void initView() {
        tvAccount = (TextView)findViewById(R.id.tv_account_text);
        tvNickName = (TextView)findViewById(R.id.tv_nick_name);
        tvAge = (TextView)findViewById(R.id.tv_age);
        tvHome = (TextView)findViewById(R.id.tv_home_text);
        tvSchool = (TextView)findViewById(R.id.tv_school_text);
        tvBirthdayTime = (TextView)findViewById(R.id.tv_birth_time_text);
        tvGender = (TextView)findViewById(R.id.tv_gender);
        tvCity = (TextView)findViewById(R.id.tv_city);
        ivAvatar = (ImageView)findViewById(R.id.iv_avatar);
        toEdit = (ImageView) findViewById(R.id.toEdit);
        toBack = (ImageView)findViewById(R.id.toBack);

    }



    public void logout(View view) {
        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();
        edit.putBoolean("isLogin", false);
        edit.apply();

        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
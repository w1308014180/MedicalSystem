package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.medicalsystem.Bean.LoginMessage;
import com.example.medicalsystem.Bean.RegisterMessage;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton, toLogin;
    private EditText registerAccount, registerPassword, registerPasswordConfirm;
    private CheckBox rgAgree;
    String username,password,passwordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        getSupportActionBar().setTitle("注册");
        ToastUtils.init(getApplication());

        initView();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = registerAccount.getText().toString();
                password = registerPassword.getText().toString();
                passwordConfirm = registerPasswordConfirm.getText().toString();
                ToastUtils.init(getApplication());
                ToastUtils.setStyle(new WhiteToastStyle());


                if (TextUtils.isEmpty(username)) {
                    ToastUtils.show("用户名不能为空");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空");
                    return;
                }

                if (!TextUtils.equals(password, passwordConfirm)) {
                    ToastUtils.show("密码不一致");
                    return;
                }

                if (!rgAgree.isChecked()) {
                    ToastUtils.show("请同意用户协议");
                    return;
                }

                //发送网络请求是否注册成功
                sendRequestWithOkHttp();

              /*  UserService uService=new UserService(RegisterActivity.this);
                User user = new User();
                user.setUsername(username);
                user.setUserpwd(password);
                uService.register(user);*/

               /* Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);*/

            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void initView(){
        registerButton = (Button) findViewById(R.id.register_button);
        registerAccount = (EditText) findViewById(R.id.register_account);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPasswordConfirm = (EditText) findViewById(R.id.register_password_confirm);
        rgAgree = (CheckBox) findViewById(R.id.cb_agree);
        toLogin = (Button)findViewById(R.id.bt_to_login);
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //访问服务器地址
                            .url("http://81.71.137.16:8000/api/v2/user/register?username="+username+"&password="+password)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        RegisterMessage registerMessage = gson.fromJson(jsonData, RegisterMessage.class);
        Log.d("Register:code","registerMessage.getCode()");
        Log.d("Register:msg",registerMessage.getMsg());
        Log.d("Register:data",registerMessage.getData());
        if(Objects.equals(registerMessage.getCode(),200)){
            ToastUtils.show("注册成功");
        }else{
            ToastUtils.show("已有该账号注册失败");
        }
    }
}
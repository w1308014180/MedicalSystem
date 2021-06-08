package com.example.medicalsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.example.medicalsystem.Bean.LoginMessage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

import org.json.JSONArray;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REGISTER = 1;
    private Button buttonLogin, register;
    private EditText loginAccount, loginPassword;
    private CheckBox cbRemember, cbAutoLogin;
    private String account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TAG = "Login";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getActionBar();
        getSupportActionBar().setTitle("登录");

        //初始化控件
        initView();

        //跳转注册页
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister(v);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = loginAccount.getText().toString().trim();
                password = loginPassword.getText().toString().trim();
                //吐司
                ToastUtils.init(getApplication());
                ToastUtils.setStyle(new WhiteToastStyle());

                Log.d(TAG, "onClick: -------------" + account);

                //访问服务器注册是否成功
                sendRequestWithOkHttp();


            }
        });


    cbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                cbRemember.setChecked(true);
            }
        }
    });

    cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isChecked) {
                cbAutoLogin.setChecked(false);
            }
        }
    });

}

    private void initView(){
        buttonLogin = (Button)findViewById(R.id.btn_login);
        loginAccount = (EditText)findViewById(R.id.login_account);
        loginPassword = (EditText)findViewById(R.id.login_password);
        cbRemember = (CheckBox)findViewById(R.id.cb_remember);
        cbAutoLogin = (CheckBox)findViewById(R.id.cb_auto_login);
        register = (Button)findViewById(R.id.to_register);
    }


    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d("Login","send---------------------");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //访问服务器
                            .url("http://81.71.137.16:8000/api/v2/user/login?username="+account+"&password="+password)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData){
        Log.d("Login","Parse JSON-----------------");
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<LoginMessage>() {}.getType();
            LoginMessage loginMessage = gson.fromJson(jsonData,type);
            if(Objects.equals(loginMessage.getCode(),200)){
                ToastUtils.show("登录成功");
                Log.d("Login","Login-----------------");
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }else{
                ToastUtils.show("登录失败（账号或密码错误）");
            }
    }

}
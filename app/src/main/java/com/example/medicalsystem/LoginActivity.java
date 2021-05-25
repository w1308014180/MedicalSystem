package com.example.medicalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText loginAccount, loginPassword;
    private CheckBox cbRemember;

    private String username = "juniper";
    private String pass = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TAG = "Login";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getActionBar();
        getSupportActionBar().setTitle("登录");

        //初始化控件
        initView();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = loginAccount.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                ToastUtils.init(getApplication());

                //判断同户名
                if(TextUtils.equals(username,account)){
                    if(TextUtils.equals(password,pass)){
                        ToastUtils.show("登录成功");
                        //勾选记住密码
                        if(cbRemember.isChecked()){
                            SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();
                            //存储 用户名、密码、记住密码勾选状态
                            edit.putString("account",account);
                            edit.putString("password",password);
                            edit.putBoolean("isRemember", true);
                            //先写入内存 空闲时写入磁盘
                            edit.apply();
                        }else{
                            SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();
                            edit.putBoolean("isRemember", false);
                            edit.apply();
                        }

                        //将用户名传入ThirdFragment
                        Bundle bundle = new Bundle();
                        bundle.putString("account",account);
                        Log.d(TAG,"Login---------"+account);
                        ThirdFragment fragment = new ThirdFragment();
                        fragment.setArguments(bundle);

                        //跳转到MainActivity
                        Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentMain);

                        //退出
                        LoginActivity.this.finish();

                    }else{
                        ToastUtils.show("密码错误");
                    }
                }else{
                    ToastUtils.show("用户名错误");
                }
            }
        });
    }

    private void initView(){
        buttonLogin = (Button)findViewById(R.id.btn_login);
        loginAccount = (EditText)findViewById(R.id.login_account);
        loginPassword = (EditText)findViewById(R.id.login_password);
        cbRemember = (CheckBox)findViewById(R.id.cb_remember);

    }


}
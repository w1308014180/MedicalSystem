package com.example.medicalsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.example.medicalsystem.Bean.User;
import com.example.medicalsystem.DataBase.UserDatabase;
import com.example.medicalsystem.DatabaseHelper.UserDatabaseHelper;

import com.example.medicalsystem.Service.UserService;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REGISTER = 1;
    private Button buttonLogin, register;
    private EditText loginAccount, loginPassword;
    private CheckBox cbRemember, cbAutoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TAG = "Login";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getActionBar();
        getSupportActionBar().setTitle("登录");

        //初始化控件
        initView();

        //跳转登录页
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister(v);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = loginAccount.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                //吐司
                ToastUtils.init(getApplication());

                Log.d(TAG, "onClick: -------------" + account);

                Log.i("TAG",account+"_"+password);
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(account, password);

                if(flag){
                    Log.i("TAG","登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }


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


}
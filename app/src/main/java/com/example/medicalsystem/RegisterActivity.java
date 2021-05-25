package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText registerAccount, registerPassword, registerPasswordConfirm;
    private CheckBox rgAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        getSupportActionBar().setTitle("注册");

        registerButton = (Button)findViewById(R.id.register_button);
        registerAccount = (EditText)findViewById(R.id.register_account);
        registerPassword = (EditText)findViewById(R.id.register_password);
        registerPasswordConfirm = (EditText)findViewById(R.id.register_password_confirm);
        rgAgree = (CheckBox) findViewById(R.id.cb_agree);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = registerAccount.getText().toString();
                String password = registerPassword.getText().toString();
                String passwordConfirm = registerPasswordConfirm.getText().toString();
                ToastUtils.init(getApplication());
                ToastUtils.setStyle(new WhiteToastStyle());


                if(TextUtils.isEmpty(username)){
                   ToastUtils.show("用户名不能为空");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    ToastUtils.show("密码不能为空");
                    return;
                }

                if(!TextUtils.equals(password, passwordConfirm)){
                    ToastUtils.show("密码不一致");
                    return;
                }

                if(!rgAgree.isChecked()){
                    ToastUtils.show("请同意用户协议");
                    return;
                }

                ToastUtils.show("注册成功!");




            }
        });

    }
}
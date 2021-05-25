package com.example.medicalsystem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    private TextView username;
    private Button exitLogin;


    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);

        username = (TextView)view.findViewById(R.id.username);
        exitLogin = (Button)view.findViewById(R.id.exit_login);

        //获取Login传入的用户名account
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            String accountData = bundle.getString("account");
            username.setText(accountData);
        }

        //点击退出登录按钮
        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(view);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThirdViewModel.class);
        // TODO: Use the ViewModel
    }

    //退出登录回到登录界面
    public void logout(View view){
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }



}
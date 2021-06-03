package com.example.medicalsystem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicalsystem.Util.ImageUtil;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ThirdFragment extends Fragment {

    private static final int MODE_PRIVATE = 0;
    private ThirdViewModel mViewModel;
    private TextView username;
    private Button exitLogin, btReport;
    private ImageView userImage;


    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        initView(view);

        SharedPreferences spfRecord = getActivity().getSharedPreferences("spfRecord",  MODE_PRIVATE);
        String image64 = spfRecord.getString("image_64", "");
        userImage.setImageBitmap(ImageUtil.base64ToImage(image64));

        //点击退出登录按钮
        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(view);
            }
        });

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), UserProfileActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //获取Login传入的用户名account
        Map<String, NavArgument> map = NavHostFragment.findNavController(this).getGraph().getArguments();
        NavArgument argument = map.get("account");
        Bundle bundle = (Bundle) argument.getDefaultValue();
        if(bundle!=null){
            String accountData = bundle.getString("account");
            username.setText(accountData);
        }

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

    public void initView(View view){
        username = (TextView)view.findViewById(R.id.tvusername);
        exitLogin = (Button)view.findViewById(R.id.exit_login);
        userImage = (ImageView)view.findViewById(R.id.icon_image);
        btReport = (Button)view.findViewById(R.id.bt_report);
    }



}
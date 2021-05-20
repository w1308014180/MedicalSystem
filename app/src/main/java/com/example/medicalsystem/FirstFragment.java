package com.example.medicalsystem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.medicalsystem.Adapter.DoctorAdapter;
import com.example.medicalsystem.Adapter.NewsAdapter;
import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.News;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView imageView;
    private List<Doctor> doctorList = new ArrayList<>();


    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        //点击地图
        ImageView mapButton = (ImageView)view.findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //医师推荐recyclerView
        initDoctor();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.doctor_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DoctorAdapter adapter = new DoctorAdapter(doctorList);
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //仅保持当前页面在旋转页面时不变 切换页面会改变
        //mViewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);切换页面不变
        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel

    }




    private void initDoctor(){
        for(int i=0; i<5; i++){
            Doctor doctor1 = new Doctor("张三", "内科","呼吸内科","教授");
            doctorList.add(doctor1);
            Doctor doctor2 = new Doctor("李四", "外科","神经外科","副教授");
            doctorList.add(doctor2);
            Doctor doctor3 = new Doctor("王五", "耳鼻咽喉科","眼科","医师");
            doctorList.add(doctor3);
        }
    }

}
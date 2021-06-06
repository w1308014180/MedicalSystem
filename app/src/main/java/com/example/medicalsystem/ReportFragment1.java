package com.example.medicalsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.medicalsystem.Adapter.RecordAdapter;
import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.ViewModel.RecordViewModel;

import java.util.List;

public class ReportFragment1 extends Fragment {

    private RecordViewModel recordViewModel;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment1 newInstance() {
        // Required empty public constructor
        return new ReportFragment1();
    }

    public static ReportFragment1 newInstance(String param1, String param2) {
        ReportFragment1 fragment = new ReportFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report1_layout, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_report1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        RecordAdapter recordAdapter = new RecordAdapter(recordViewModel);
        recyclerView.setAdapter(recordAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recordAdapter);

        //获取列表 观察
        recordViewModel.getAllRecordLive().observe(getViewLifecycleOwner(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                int temp = recordAdapter.getItemCount();
                recordAdapter.setAllRecord(records);
                if(temp != records.size()){
                    recordAdapter.notifyDataSetChanged();
                }
            }
        });

        //初始化数据
       //initData();

        return view;
    }

    private void initData(){
        Record record1 = new Record("发烧、喉咙痛",1,"2020.12.05 14:30",2,"王三"+"医生");
        Record record2 = new Record("视力模糊、头疼、眼胀",1,"2021.06.03 5:30",2,"李一"+"医生");
        Record record3 = new Record("复查眼压",1,"2021.07.05 9:45",2,"李一"+"医生");
        recordViewModel.insertRecord(record1);
        recordViewModel.insertRecord(record2);
        recordViewModel.insertRecord(record3);
        //recordViewModel.deleteAllRecords(record1);
    }
}
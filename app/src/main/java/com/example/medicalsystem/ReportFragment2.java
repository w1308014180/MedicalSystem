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


import com.example.medicalsystem.Adapter.ResultAdapter;
import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.Bean.Result;
import com.example.medicalsystem.ViewModel.ResultViewModel;

import java.util.List;

public class ReportFragment2 extends Fragment {

    private ResultViewModel resultViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment2() {
        // Required empty public constructor
    }


    public static ReportFragment2 newInstance(String param1, String param2) {
        ReportFragment2 fragment = new ReportFragment2();
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
        View view =  inflater.inflate(R.layout.fragment_report2_layout, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_report2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        ResultAdapter resultAdapter = new ResultAdapter(resultViewModel);
        recyclerView.setAdapter(resultAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(resultAdapter);

        //???????????? ??????
        resultViewModel.getAllResultLive().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                int temp = resultAdapter.getItemCount();
                resultAdapter.setAllResult(results);
                if(temp != results.size()){
                    resultAdapter.notifyDataSetChanged();
                }
            }
        });

        //???????????????
        //initData();
        return view;
    }

    private void initData(){
        Result result0 = new Result("?????????",R.drawable.temp,"????????????????????????","2020.12.05 14:36");
        Result result1 = new Result("????????????",R.drawable.eye2,"??????????????????????????????????????????????????????????????????","2021.06.03 5:45");
        Result result2 = new Result("????????????",R.drawable.eye,"???????????????????????????????????????????????????????????????????????????????????????","2021.06.03 5:53");
        Result result3 = new Result("????????????",R.drawable.eye3,"?????????????????????????????????","2021.07.05 10:00");
        resultViewModel.insertResult(result0);
        resultViewModel.insertResult(result1);
        resultViewModel.insertResult(result2);
        resultViewModel.insertResult(result3);
        //resultViewModel.deleteAllResults(result0);
    }
}
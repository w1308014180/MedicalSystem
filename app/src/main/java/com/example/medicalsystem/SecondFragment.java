package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.Adapter.NewsAdapter;
import com.example.medicalsystem.Bean.News;

import java.util.ArrayList;
import java.util.List;

import static com.example.medicalsystem.R.menu.menu;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    //资讯列表
    private List<News> newsList = new ArrayList<>();

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        /*使用Toolbar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity context = (AppCompatActivity)getActivity();
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setTitle("");
        if(context.getSupportActionBar() != null){
            context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            context.getSupportActionBar().setHomeButtonEnabled(true);
        }

        setHasOptionsMenu(true);*/

        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        initNews();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel

    }

    private void initNews(){
        for(int i=0; i<2; i++){
            News news1 = new News("健康小常识1","好大夫","2021.5.20");
            newsList.add(news1);
            News news2 = new News("健康小常识2","丁香医生","2021.5.20");
            newsList.add(news2);
            News news3 = new News("健康小常识3","阿里健康","2021.5.20");
            newsList.add(news3);
            News news4 = new News("健康小常识4","百度在线医生","2021.5.20");
            newsList.add(news4);
            News news5 = new News("健康小常识5","某网友","2021.5.20");
            newsList.add(news5);
            News news6 = new News("健康小常识6","好大夫","2021.5.20");
            newsList.add(news6);
        }
    }



}
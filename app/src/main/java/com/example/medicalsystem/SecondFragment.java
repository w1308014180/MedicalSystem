package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.Adapter.NewsAdapter;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.ViewModel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.medicalsystem.R.menu.menu;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private String username;

    //资讯列表
    ImageView insert_news;
    NewsViewModel newsViewModel;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ImageView editNews;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);


        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        NewsAdapter newsAdapter = new NewsAdapter(newsViewModel);
        recyclerView.setAdapter(newsAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsAdapter);

        ImageView editNews = (ImageView)view.findViewById(R.id.news_insert);

        //获取用户名
        getUsername();


        editNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditNewsActivity.class);
                intent.putExtra("Username",username);
                getActivity().startActivity(intent);
            }
        });

        //获取列表 观察
        newsViewModel.getAllNewsLive().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                int temp = newsAdapter.getItemCount();
                newsAdapter.setAllNews(news);
                if(temp != news.size()){
                    newsAdapter.notifyDataSetChanged();
                }
            }
        });
        /*insert_news = (ImageView)view.findViewById(R.id.news_insert);

        insert_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = new News("健康小贴士","好医生",2,"5.24","喝热水");
                newsViewModel.insertNews(news);
            }
        });*/


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel

    }

    private void getUsername(){
        //获取Login传入的用户名account
        Map<String, NavArgument> map = NavHostFragment.findNavController(this).getGraph().getArguments();
        NavArgument argument = map.get("account");
        Bundle bundle = (Bundle) argument.getDefaultValue();
        if(bundle!=null){
            String accountData = bundle.getString("account");
            username = accountData;
        }
    }





}
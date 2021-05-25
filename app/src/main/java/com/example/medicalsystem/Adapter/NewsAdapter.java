package com.example.medicalsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.R;
import com.example.medicalsystem.ViewModel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<News> allNews = new ArrayList<>();
    NewsViewModel newsViewModel;

    public NewsAdapter(NewsViewModel newsViewModel) {
        this.newsViewModel = newsViewModel;
    }

    public void setAllNews(List<News>allNews){
        this.allNews = allNews;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView);
    }


    //逻辑上关联
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = allNews.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsSource.setText(news.getSource());
        holder.newsDelete.setOnClickListener(null);
        int deleteId = news.getId();
        holder.newsTime.setText(news.getTime());

        //用户可以删除自己编写的news
        if(news.getSourceId()!=1){
            holder.newsDelete.setVisibility(View.GONE);
        }else{
            holder.newsDelete.setVisibility(View.VISIBLE);
        }

        //按钮监听
        holder.newsDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                News news = new News("健康小贴士","好医生",2,"5.24","六味地黄丸");
                news.setId(deleteId);
                newsViewModel.deleteNews(news);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitle, newsSource, newsTime;
        Button newsDelete;
        public NewsViewHolder(@NonNull View itemView){
            super((itemView));
            newsTitle = itemView.findViewById(R.id.title);
            newsSource = itemView.findViewById(R.id.source);
            newsTime = itemView.findViewById(R.id.time);
            newsDelete = itemView.findViewById(R.id.news_delete);
        }
    }



}


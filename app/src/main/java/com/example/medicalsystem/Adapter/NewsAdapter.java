package com.example.medicalsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.R;

import java.util.List;
import java.util.ListIterator;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView source;
        TextView time;

        public ViewHolder(View view){
            super(view);
            //获取布局实例
            title = (TextView)view.findViewById(R.id.title);
            source = (TextView)view.findViewById(R.id.source);
            time = (TextView)view.findViewById(R.id.time);
        }
    }

    public NewsAdapter(List<News>newsList){
        mNewsList = newsList;
    }

    @NonNull
    @Override
    //创建ViewHolder实例
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载子布局news_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent,false);
        //创建viewholder实例 加载出来的布局传入构造函数
        ViewHolder holder = new ViewHolder(view);
        //返回实例
        return holder;
    }

    @Override
    //对recyclerview子项进行赋值
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.title.setText(news.getTitle());
        holder.source.setText(news.getSource());
        holder.time.setText(news.getTime());
    }

    //子项数量
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }




}


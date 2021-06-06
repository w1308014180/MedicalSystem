package com.example.medicalsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.medicalsystem.Bean.Result;
import com.example.medicalsystem.R;
import com.example.medicalsystem.ViewModel.ResultViewModel;


import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{

    List<Result> allResult = new ArrayList<>();
    ResultViewModel resultViewModel;

    public ResultAdapter(ResultViewModel resultViewModel) {
        this.resultViewModel = resultViewModel;
    }

    public void setAllResult(List<Result>allResult){
        this.allResult = allResult;
    }

    @NonNull
    @Override
    public ResultAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.result_item, parent, false);
        return new ResultAdapter.ResultViewHolder(itemView);
    }


    //逻辑上关联
    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ResultViewHolder holder, int position) {
        Result result = allResult.get(position);
        holder.tvResultProject.setText(result.getProject());
        holder.tvResultContent.setText(result.getResult());
        holder.tvResultTime.setText(result.getTime());
        holder.ivResultPic.setImageResource(result.getImageLoc());
    }

    @Override
    public int getItemCount() {
        return allResult.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder{
        TextView tvResultProject, tvResultContent, tvResultTime;
        ImageView ivResultPic;

        public ResultViewHolder(@NonNull View itemView){
            super((itemView));
            tvResultProject = (TextView)itemView.findViewById(R.id.tv_result_project);
            tvResultContent = (TextView)itemView.findViewById(R.id.tv_result_content);
            tvResultTime = (TextView)itemView.findViewById(R.id.tv_result_time);
            ivResultPic = (ImageView)itemView.findViewById(R.id.iv_result_pic);
        }

    }

}


package com.example.medicalsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.R;
import com.example.medicalsystem.ViewModel.RecordViewModel;


import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{

    List<Record> allRecord = new ArrayList<>();
    RecordViewModel recordViewModel;

    public RecordAdapter(RecordViewModel recordViewModel) {
        this.recordViewModel = recordViewModel;
    }

    public void setAllRecord(List<Record>allRecord){
        this.allRecord = allRecord;
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.record_item, parent, false);
        return new RecordAdapter.RecordViewHolder(itemView);
    }


    //逻辑上关联
    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, int position) {
        Record record = allRecord.get(position);
        holder.tvRecordContent.setText(record.getRecordContent());
        holder.tvRecordDotorName.setText(record.getRecordDoctorName());
        holder.tvRrecordTime.setText(record.getRecordTime());

    }

    @Override
    public int getItemCount() {
        return allRecord.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView tvRecordContent, tvRecordDotorName, tvRrecordTime;

        public RecordViewHolder(@NonNull View itemView){
            super((itemView));
            tvRecordContent = itemView.findViewById(R.id.tv_record_content);
            tvRecordDotorName = itemView.findViewById(R.id.tv_record_doctor_name);
            tvRrecordTime = itemView.findViewById(R.id.tv_record_time);
        }

    }

}

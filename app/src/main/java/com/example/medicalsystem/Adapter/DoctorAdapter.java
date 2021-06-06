package com.example.medicalsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{
    private List<Doctor> mDoctorList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName;
        TextView doctorDepartmentName;
        TextView doctorSubjectName;
        TextView doctorJobTile;
        CircleImageView doctorImage;


        public ViewHolder(View view){
            super(view);
            //获取布局实例
            doctorName = (TextView)view.findViewById(R.id.doctor_name);
            doctorDepartmentName = (TextView)view.findViewById(R.id.doctor_department_name);
            doctorSubjectName = (TextView)view.findViewById(R.id.docotr_subject_name);
            doctorJobTile = (TextView)view.findViewById(R.id.doctor_job_title);
            doctorImage = (CircleImageView)view.findViewById(R.id.ci_suggested_doctor);
        }
    }

    public DoctorAdapter(List<Doctor> doctorList){
        mDoctorList = doctorList;
    }
    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载子布局news_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent,false);
        //创建viewholder实例 加载出来的布局传入构造函数
        DoctorAdapter.ViewHolder doctorHolder = new DoctorAdapter.ViewHolder(view);
        //返回实例
        return doctorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        Doctor doctor = mDoctorList.get(position);
        holder.doctorName.setText(doctor.getDoctorName());
        holder.doctorDepartmentName.setText(doctor.getDepartmentName());
        holder.doctorSubjectName.setText(doctor.getSubjectName());
        holder.doctorJobTile.setText(doctor.getJobTile());
        holder.doctorImage.setImageResource(doctor.getDoctorImageLoc());
    }

    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }
}

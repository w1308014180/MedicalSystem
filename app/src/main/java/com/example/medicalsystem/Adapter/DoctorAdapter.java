package com.example.medicalsystem.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.DoctorSchedule;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Bean.Reservation;
import com.example.medicalsystem.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.toast.ToastUtils;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{
    private static final String TAG = "DoctorAdapter";
    private List<Doctor> mDoctorList;
    private int mflag = 0;
    private int mdoctorId, mindex;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName;
        TextView doctorDepartmentName;
        TextView doctorSubjectName;
        TextView doctorJobTile;
        CircleImageView doctorImage;
        Button toReserve;


        public ViewHolder(View view){
            super(view);
            //获取布局实例
            doctorName = (TextView)view.findViewById(R.id.doctor_name);
            doctorDepartmentName = (TextView)view.findViewById(R.id.doctor_department_name);
            doctorSubjectName = (TextView)view.findViewById(R.id.docotr_subject_name);
            doctorJobTile = (TextView)view.findViewById(R.id.doctor_job_title);
            doctorImage = (CircleImageView)view.findViewById(R.id.ci_suggested_doctor);
            toReserve = (Button)view.findViewById(R.id.btn_to_reserve);
        }
    }

    public DoctorAdapter(List<Doctor> doctorList, int flag){
        mDoctorList = doctorList;
        mflag = flag;
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
        //获取医生id
        mdoctorId = doctor.getDoctorId();
        mindex = position;
        Log.d(TAG,"flag is "+mflag);

        //医生推荐列表不显示预约按钮 可预约医生列表显示预约按钮
        if(mflag==1){
            Log.d(TAG,"visible");
            holder.toReserve.setVisibility(View.VISIBLE);
        }else {
            holder.toReserve.setVisibility(View.INVISIBLE);
        }

        holder.toReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }

    //请求后台
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d(TAG,"sendRequestWithOkHttp---------------------");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //访问服务器查询可预约医生列表
                            .url("http://81.71.137.16:8000/api/v2/doctor/booking?doctor_id="+mdoctorId+"&index="+mindex)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){

                }
            }
        }).start();
    }

    //解析json
    private void parseJSONWithJSONObject(String jsonData){
        Log.d(TAG,"Parse JSON-----------------");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<DoctorSchedule>() {}.getType();
        Reservation reservation = gson.fromJson(jsonData,type);
        if(Objects.equals(reservation.getCode(),200)){
            ToastUtils.show("预约成功");
            Log.d(TAG,"预约成功-----------------");
        }else{
            ToastUtils.show("预约失败");
        }
    }
}

package com.example.medicalsystem;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.medicalsystem.Adapter.DoctorAdapter;
import com.example.medicalsystem.Adapter.SpinnerAdapter;
import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.DoctorSchedule;
import com.example.medicalsystem.Bean.Json;
import com.example.medicalsystem.Bean.LoginMessage;
import com.example.medicalsystem.Util.GetJsonDataUtil;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "ReservationActivity";
    private List<Json> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private Button btnShowSubject,btnSearchDoctor;
    private TextView tvTime;
    private Spinner spinner;
    private String[] dateToSelect;
    private int spinnerPosition;
    private RecyclerView rvReservationDoctor;
    private List<Doctor> reservationDoctorList = new ArrayList<>();
    private String opt1tx,opt2tx;
    private int mflag = 1;


    private static boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_layout);
        //??????????????????
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //??????
        ToastUtils.init(getApplication());
        ToastUtils.setStyle(new WhiteToastStyle());
        //??????????????????Spinner
        initView();
        //????????????
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        btnShowSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();
            }
        });

        Context mcontext = this;

        btnSearchDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //??????????????????
                sendRequestWithOkHttp();
                //?????????????????????
                LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
                rvReservationDoctor.setLayoutManager(layoutManager);
                DoctorAdapter adapter = new DoctorAdapter(reservationDoctorList,1);
                rvReservationDoctor.setAdapter(adapter);

            }
        });


    }


    //??????????????????spinner
    private void initView() {
        btnShowSubject = (Button) findViewById(R.id.btn_showSubject);
        btnSearchDoctor = (Button)findViewById(R.id.btn_search_doctor);
        tvTime = (TextView)findViewById(R.id.tv_time);
        rvReservationDoctor = (RecyclerView)findViewById(R.id.rv_reservation_doctor);

        //????????????
        SimpleDateFormat formatter =  new SimpleDateFormat("MM???dd???");
        Date curDate0 =  new Date(System.currentTimeMillis());
        Date curDate1 =  new Date(System.currentTimeMillis()+86400000);
        Date curDate2 =  new Date(System.currentTimeMillis()+86400000*2);
        Date curDate3 =  new Date(System.currentTimeMillis()+86400000*3);
        Date curDate4 =  new Date(System.currentTimeMillis()+86400000*4);
        Date curDate5 =  new Date(System.currentTimeMillis()+86400000*5);
        Date curDate6 =  new Date(System.currentTimeMillis()+86400000*6);
        String day0 = formatter.format(curDate0);
        String day1 = formatter.format(curDate1);
        String day2 = formatter.format(curDate2);
        String day3 = formatter.format(curDate3);
        String day4 = formatter.format(curDate4);
        String day5 = formatter.format(curDate5);
        String day6 = formatter.format(curDate6);
        dateToSelect = new String[]{day0, day1, day2, day3, day4, day5, day6};
        //???????????????
        spinner = (Spinner) findViewById(R.id.spinner);
        //????????????????????????????????????spinner
        spinner.setAdapter(new SpinnerAdapter(dateToSelect,this));
        //????????????
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //???item???????????????????????????
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //????????????????????????
                String s = dateToSelect[position];
                spinnerPosition = position;
                //???????????????????????????????????????
                ToastUtils.show("????????????"+position);
                sendRequestWithOkHttp();
            }
            //?????????patent???????????????????????????????????????
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//????????????????????????????????????????????????
                        Toast.makeText(ReservationActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // ?????????????????????????????????
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    Toast.makeText(ReservationActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(ReservationActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void initJsonData() {//????????????


        String JsonData = new GetJsonDataUtil().getJson(this, "department.json");//??????assets????????????json????????????

        ArrayList<Json> jsonBean = parseData(JsonData);//???Gson ????????????


        //????????????
        options1Items = jsonBean;
        int len = jsonBean.size();
        Log.d(TAG, "jsonBean--------" + "len");
        //????????????
        for (int i = 0; i < jsonBean.size(); i++) {
            //????????????????????? ??????
            ArrayList<String> subjectList = new ArrayList<>();

            //??????????????????????????????
            for (int c = 0; c < jsonBean.get(i).getSubjectList().size(); c++) {
                String subjectName = jsonBean.get(i).getSubjectList().get(c).getName();
                //????????????
                subjectList.add(subjectName);
            }

           //??????????????????
            options2Items.add(subjectList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    //Gson ??????
    public ArrayList<Json> parseData(String result) {
        ArrayList<Json> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                Json entity = gson.fromJson(data.optJSONObject(i).toString(), Json.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    // ???????????????
    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                //??????
                /*String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                 */
                //String tx = opt1tx + opt2tx +opt3tx;
                String tx = opt1tx + opt2tx;
                btnShowSubject.setText(opt1tx+" "+opt2tx);
                Toast.makeText(ReservationActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("??????????????????")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//???????????????*/
        pvOptions.setPicker(options1Items, options2Items);//???????????????
        //pvOptions.setPicker(options1Items, options2Items, options3Items);//???????????????
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    //??????????????????
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    //????????????
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d(TAG,"sendRequestWithOkHttp---------------------");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //??????????????????????????????????????????
                            .url("http://81.71.137.16:8000/api/v2/doctor/search?department="+opt1tx+"&subject="
                                    +opt2tx+"&index="
                                    +spinnerPosition)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){

                }
            }
        }).start();
    }

    //??????json
    private void parseJSONWithJSONObject(String jsonData){
        Log.d(TAG,"Parse JSON-----------------");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<DoctorSchedule>() {}.getType();
        DoctorSchedule doctorSchedule = gson.fromJson(jsonData,type);
        List<DoctorSchedule.data> doctorList = doctorSchedule.getData();
        if(Objects.equals(doctorSchedule.getCode(),"200")){
            ToastUtils.show("????????????");
            Log.d(TAG,"????????????-----------------");
            for(DoctorSchedule.data doctor:doctorList){
                //????????????????????????????????????data ???????????????recyclerView?????????
                Doctor doctorItem = new Doctor(doctor.getDoctor_name(), doctor.getDepartment_name(),doctor.getSubject_name(),doctor.getJob_title(),R.drawable.ic_baseline_emoji_emotions_24);
                reservationDoctorList.add(doctorItem);
                Log.d(TAG,doctor.getDoctor_name());
            }
            }else{
            ToastUtils.show("????????????");
        }
    }

}

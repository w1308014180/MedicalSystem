package com.example.medicalsystem;


import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.example.medicalsystem.Adapter.SpinnerAdapter;
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
    private Calendar c;
    private Spinner spinner;
    private String[] dateToSelect;
    private int spinnerPosition;


    private static boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_layout);
        //启动返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //吐司
        ToastUtils.init(getApplication());
        ToastUtils.setStyle(new WhiteToastStyle());
        //初始化控件和Spinner
        initView();
        //解析数据
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        btnShowSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();
            }
        });

        btnSearchDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    //初始化控件和spinner
    private void initView() {
        btnShowSubject = (Button) findViewById(R.id.btn_showSubject);
        btnSearchDoctor = (Button)findViewById(R.id.btn_search_doctor);
        tvTime = (TextView)findViewById(R.id.tv_time);

        //获取日期
        SimpleDateFormat formatter =  new SimpleDateFormat("MM月dd日");
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
        dateToSelect = new String[]{day0, day1, day2, day2, day3, day4, day5, day6};
        //控件初始化
        spinner = (Spinner) findViewById(R.id.spinner);
        //自定义适配器，将其设置给spinner
        spinner.setAdapter(new SpinnerAdapter(dateToSelect,this));
        //设置监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //当item被选择后调用此方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取所选中的内容
                String s = dateToSelect[position];
                spinnerPosition = position;
                //弹一个吐司提示所选中的内容
                ToastUtils.show("你选择了"+position);
            }
            //只有当patent中的资源没有时，调用此方法
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
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        Toast.makeText(ReservationActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
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

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "department.json");//获取assets目录下的json文件数据

        ArrayList<Json> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        //添加科室
        options1Items = jsonBean;
        int len = jsonBean.size();
        Log.d(TAG, "jsonBean--------" + "len");
        //遍历科室
        for (int i = 0; i < jsonBean.size(); i++) {
            //科室的科目列表 二级
            ArrayList<String> subjectList = new ArrayList<>();

            //遍历该科室的所有科目
            for (int c = 0; c < jsonBean.get(i).getSubjectList().size(); c++) {
                String subjectName = jsonBean.get(i).getSubjectList().get(c).getName();
                //添加科目
                subjectList.add(subjectName);
            }

           //添加科目数据
            options2Items.add(subjectList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    //Gson 解析
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

    // 弹出选择器
    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                //三级
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

                .setTitleText("诊疗科目选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        //pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    //监听返回事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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
                            //访问服务器
                            .url("http://81.71.137.16:8000/api/v2/doctor/search?department="+"内科"+"&subject="
                                    + "高血压内分泌科"+"&index="
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

    //解析json
    private void parseJSONWithJSONObject(String jsonData){
        Log.d("Login","Parse JSON-----------------");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<LoginMessage>() {}.getType();
        LoginMessage loginMessage = gson.fromJson(jsonData,type);
        if(Objects.equals(loginMessage.getCode(),200)){
            ToastUtils.show("登录成功");
            Log.d("Login","Login-----------------");

        }else{
            ToastUtils.show("登录失败（账号或密码错误）");
        }
    }

}

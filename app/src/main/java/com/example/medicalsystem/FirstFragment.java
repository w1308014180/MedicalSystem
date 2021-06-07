package com.example.medicalsystem;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.Adapter.DoctorAdapter;
import com.example.medicalsystem.Adapter.NewsAdapter;
import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.News;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import de.hdodenhof.circleimageview.CircleImageView;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView imageView;
    private List<Doctor> doctorList = new ArrayList<>();
    private ImageView mapButton;
    private Button toReserve, toReport;
    private RecyclerView recyclerView;
    private BGABanner mBGABanner;
    private List<String>imgList;

    //模拟数据
    private String[] picUrl={
        "https://www.toutiao.com/a6961319733358641695/?log_from=3ef5f9fa26f05_1622913471440",
            "https://www.toutiao.com/a6970298653432955400/?log_from=8cefffb2df69c_1622913545229",
            "https://www.toutiao.com/a6970273812151534116/?log_from=593ade749fb9e_1622913574934"
    };

    private String[] picDes={
        "患了高血压后，请自觉管住嘴：这5种食物劝你不要碰",
            "印度再传坏消息！患者罕见感染“三色真菌”，内脏竟被溶解",
            "刚刚！广州宣布，南沙区公交地铁停运！一高三学子感染，将在医院单独高考！广东疫情感染链增至96人"
    };



    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        //点击地图
        initView(view);

        //点击进入地图功能
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //点击预约挂号
        toReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ReservationActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //查看检查结果
        toReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ReportActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //医生推荐recyclerView
        //initDoctor();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //0则不显示预约按钮
        DoctorAdapter adapter = new DoctorAdapter(doctorList, 0);
        recyclerView.setAdapter(adapter);

        imgList = new ArrayList<>();
        List<String> imgDsec = new ArrayList<>();

        for(int i=0; i<picUrl.length; i++){
            imgList.add(picUrl[i]);
            imgDsec.add(picDes[i]);
        }


        mBGABanner.setData(imgList, imgDsec);
        //适配器
        mBGABanner.setAdapter(new BGABanner.Adapter<ImageView, String>(){

            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(getActivity())
                        .load(model)
                        .placeholder(R.drawable.holder)
                        .error(R.drawable.holder)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);

            }
        });

        //设置banner图片url及标题
        mBGABanner.setData(Arrays.asList( "https://p1-tt.byteimg.com/origin/pgc-image/8b2f5409b36a4b2c8f58fda45a3adb64?from=pc",
                "https://p3-tt.byteimg.com/origin/pgc-image/e5738f67d16f45099cd9fc9b4f2dcd1b?from=pc",
                "https://p3-tt.byteimg.com/origin/pgc-image/873cdd25766640ccb434a13121878109?from=pc"), Arrays.asList("牛奶和豆浆到底差在哪儿？",
                "夏季建议多吃5种高钾食物",
                "晨起9个养生动作"));

        mBGABanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                            intent.setClass(getActivity(),BannerWebView1.class);
                            startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(),BannerWebView2.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(getActivity(),BannerWebView3.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        //初始化医生数据
         initDoctor();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //仅保持当前页面在旋转页面时不变 切换页面会改变
        //mViewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);切换页面不变
        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel

    }




    private void initDoctor(){
        Doctor doctor1 = new Doctor("钱二", "护理门诊","胃肠外科护理门诊","主治医师",R.drawable.qian2);
        doctorList.add(doctor1);
        Doctor doctor2 = new Doctor("孙三", "护理门诊","肝胆外科护理门诊","副主任医师",R.drawable.sun3);
        doctorList.add(doctor2);
        Doctor doctor3 = new Doctor("李四", "重症医学科","重症医学科门诊","主任医师",R.drawable.sun3);
        doctorList.add(doctor3);
        Doctor doctor4 = new Doctor("吴二", "内科","血液内科门诊","主治医师",R.drawable.wu2);
        doctorList.add(doctor4);
        Doctor doctor5 = new Doctor("郑三", "内科","心血管内科门诊","副主任医师",R.drawable.wang1);
        doctorList.add(doctor5);
        Doctor doctor6 = new Doctor("王四", "外科","胃结直肠肛门门诊","主任医师",R.drawable.wang4);
        doctorList.add(doctor6);
        Doctor doctor7 = new Doctor("王五", "耳鼻咽喉科","眼科","医师",R.drawable.li4);
        doctorList.add(doctor7);
        Doctor doctor8 = new Doctor("张三", "外科","骨科","主任医师",R.drawable.zhang3);
        doctorList.add(doctor8);
        Doctor doctor9= new Doctor("李二", "专科","儿科门诊","主任医师",R.drawable.li2);
        doctorList.add(doctor9);

    }

    public void initView(View view){
       mapButton = (ImageView)view.findViewById(R.id.map_button);
       toReserve = (Button)view.findViewById(R.id.reserve);
       recyclerView = (RecyclerView)view.findViewById(R.id.doctor_recycler_view);
       toReport = (Button)view.findViewById(R.id.bt_report);
       mBGABanner = (BGABanner)view.findViewById(R.id.banner_guide_content);

    }

}
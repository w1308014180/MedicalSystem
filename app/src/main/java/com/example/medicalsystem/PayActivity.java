package com.example.medicalsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.medicalsystem.Adapter.DoctorAdapter;
import com.example.medicalsystem.Adapter.PaymentAdapter;
import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.Payment;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity {
    private double totalMoney = 0.0;
    private RecyclerView rvPayment;
    private List<Payment> paymentList = new ArrayList<>();
    private TextView tvTotalMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initBill();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPayment.setLayoutManager(layoutManager);
        //0则不显示预约按钮
        PaymentAdapter adapter = new PaymentAdapter(paymentList);
        rvPayment.setAdapter(adapter);
        totalMoney = ComputeTotalMoney();
        tvTotalMoney.setText(String.valueOf(totalMoney));

    }


    public void initView(){
        rvPayment = (RecyclerView)findViewById(R.id.rv_pay_bill);
        tvTotalMoney = (TextView)findViewById(R.id.tv_totalMoney);
    }

    public void initBill(){
        Payment payment1 = new Payment("999感冒灵",9.00,3,1);
        Payment payment2 = new Payment("连花清瘟胶囊",10.00,2,1);
        Payment payment3 = new Payment("美开朗（盐酸卡替洛尔滴眼液",30.00,1,1);
        Payment payment4 = new Payment("眼压检测",50.00,1,1);
        Payment payment5 = new Payment("视野检查",100.00,1,1);
        Payment payment6 = new Payment("挂号费", 5.00,1,1);
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);
        paymentList.add(payment5);
        paymentList.add(payment6);

    }

    private double ComputeTotalMoney(){
        double total = 0;
        for(int i=0; i<paymentList.size(); i++){
            Payment payment = paymentList.get(i);
            total += payment.getPaymentPrice()*payment.getPaymentQuantity();
        }
        return total;
    }


}
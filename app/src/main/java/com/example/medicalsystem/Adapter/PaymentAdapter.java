package com.example.medicalsystem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.medicalsystem.Bean.Doctor;
import com.example.medicalsystem.Bean.Payment;
import com.example.medicalsystem.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{
    private static final String TAG = "PaymentAdapter";
    private List<Payment> mPaymentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPaymentName, tvPaymentPrice, tvPaymentQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取布局实例
            tvPaymentName = (TextView) itemView.findViewById(R.id.tv_payment_name);
            tvPaymentPrice = (TextView) itemView.findViewById(R.id.tv_payment_price);
            tvPaymentQuantity = (TextView) itemView.findViewById(R.id.tv_payment_quantity);
        }

    }

    public PaymentAdapter(List<Payment> paymentList) {
            mPaymentList = paymentList;
        }

        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载子布局news_item
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false);
            //创建viewholder实例 加载出来的布局传入构造函数
            ViewHolder paymentHolder = new ViewHolder(view);
            //返回实例
            return paymentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        Payment payment = mPaymentList.get(position);
        holder.tvPaymentName.setText(payment.getPaymentName());
        //浮点数转字符串
        holder.tvPaymentPrice.setText(new StringBuilder().append(String.valueOf(payment.getPaymentPrice())).append("￥").toString());
        holder.tvPaymentQuantity.setText(new StringBuilder().append("x").append(payment.getPaymentQuantity()).toString());

    }

    @Override

    public int getItemCount() {
        return mPaymentList.size();
    }
}

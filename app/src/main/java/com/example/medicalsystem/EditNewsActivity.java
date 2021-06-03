package com.example.medicalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.ViewModel.NewsViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditNewsActivity extends AppCompatActivity {

    private static final String TAG = "EditNewsActivity";
    EditText editNewsTitle, editNewsContent;
    Button newsSubmit;
    NewsViewModel newsViewModel;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_news_layout);
        //获取各组件
        initView();
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        //启动返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //获取用户名
        username = getIntent().getStringExtra("Username");


        newsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                News newNews = new News(editNewsTitle.getText().toString().trim()
                        , username
                        , 2
                        , str
                        ,editNewsContent.getText().toString().trim());
                newsViewModel.insertNews(newNews);
            }
        });



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

    private void initView(){
        //获取各组件
        newsSubmit = (Button)findViewById(R.id.submit_news);
        editNewsTitle = (EditText) findViewById(R.id.edit_news_title);
        //editNewsSource = (EditText) findViewById(R.id.edit_news_source);
        editNewsContent = (EditText)findViewById(R.id.edit_news_content);
    }
}
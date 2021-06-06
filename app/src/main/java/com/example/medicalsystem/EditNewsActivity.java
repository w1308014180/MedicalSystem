package com.example.medicalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicalsystem.Bean.LoginMessage;
import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Bean.UserInfo;
import com.example.medicalsystem.ViewModel.NewsViewModel;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditNewsActivity extends AppCompatActivity {

    private static final String TAG = "EditNewsActivity";
    EditText editNewsTitle, editNewsContent;
    Button newsSubmit;
    NewsViewModel newsViewModel;
    String username;
    int userId;

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

        //获取用户id
        sendRequestWithOkHttp();


        //initData();

        newsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                News newNews = new News(editNewsTitle.getText().toString().trim()
                        , username
                        , userId
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

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //访问服务器
                            .url("http://81.71.137.16:8000/api/v2/user/info?username="+username)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){

                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData){
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(jsonData,UserInfo.class);
        userId = userInfo.getData().getId();
        if(Objects.equals(userId,200)){
            Log.d(TAG,"Get the user id succeed!"+"userId");
        }else{
            Log.d(TAG,"Get the user if failed!");
        }
    }

    public void initData(){
        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        News newNews1 = new News("中暑的紧急救护"
                , "wkk"
                , userId
                , str
                ,"将患者迅速移至阴凉或有空调处，用凉水或冰水毛巾敷于患者头部、颈部、腋下、大腿根部，有条件者还可以用冰袋、冰枕或冰块为患者冷敷;用冷水或30％的酒精擦浴，直到皮肤发红，以促使热量散发;冷水擦身加电风扇吹风;");
        newsViewModel.insertNews(newNews1);
        News newNews2 = new News("冻伤的急救措施"
                , "Juniper"
                , userId
                , str
                ,"冻伤发生后，应迅速用棉被保护受冻部位，并迅速将患者护送到室温20℃―30℃的室内。 冷温水交替复温用10℃的冷水和38℃的温水交替浸泡受冻部位20―30分钟，直至受冻部位恢复感觉，皮肤转为红紫、变软为止。如果鞋袜、手套和手足冻结在一起，应一同浸入温水内，待受冻部位恢复感觉后再用剪刀进行分离。" +
                "涂抹冻伤药膏：冻疮尚无水泡糜烂时，可用樟脑醑、冻疮酊、辣椒酊涂于患处;有溃疡的重伤号，首先应用生理盐水反复清洗创面，外涂新霉素霜，然后用无菌纱布 包扎");
        newsViewModel.insertNews(newNews2);
        News newNews3 = new News("鼻出血的处理"
                , "red"
                , userId
                , str
                ,"（1）捏紧鼻腔，安静地伸长下巴用口进行呼吸，数分钟后便可停止。\n" +
                "\n" +
                "　　（2）用冷水在鼻以上的额头部位进行冷敷。\n" +
                "\n" +
                "　　（3）不要用脱脂棉花或草纸等堵塞鼻腔，因为使用这些多纤维质的东西堵塞时，会因鼻中留下的纤维质，引起再度出血的现象，所以最好用卷扎好的纱布塞入。\n" +
                "\n" +
                "　　（4）止血后不要在短时间内再大力地捏擦鼻腔，以免再度流血。如双鼻腔流血，将双手举起，如左鼻腔流血，将右手举起;如右鼻腔流血，将左手举起。此种方法可迅速止血。");
        newsViewModel.insertNews(newNews3);
        News newNews4 = new News("一氧化碳(CO)中毒(有害气体中毒)"
                , "wkk"
                , userId
                , str
                ,"（1）打开门窗或脱离现场，呼吸新鲜空气;解开衣扣，使呼吸道通畅，有条件吸氧、注意保暖;\n" +
                "\n" +
                "　　（2）昏迷病人头偏一侧，出现抽搐针刺人中合谷穴;\n" +
                "\n" +
                "　　（3）呼吸心跳停止，立即进行徒手心肺复苏术(CPR)，呼叫120。\n" +
                "\n" +
                "　　（4）把病人送到高压氧舱，以促进碳氧血红蛋白离解和一氧化碳排出体外。 有条件吸氧、注意保暖。");
        newsViewModel.insertNews(newNews4);
        News newNews5 = new News("医学小常识之血常规"
                , "red"
                , userId
                , str
                ,"血液主要由两部分组成：血浆和血细胞。其中血细胞大致可分为白细胞、红细胞和血小板。血常规即是对血细胞进行检测的一项检验，和尿常规、便常规合称为临床三大基本检验项目，是临床应用最广泛的检验项目之一。在血常规中，我们不仅仅需要关注各种血细胞的数");
        newsViewModel.insertNews(newNews5);

    }
}
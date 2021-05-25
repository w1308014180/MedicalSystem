package com.example.medicalsystem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Dao.NewsDao;
import com.example.medicalsystem.Repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<News>> getAllNewsLive() {
        return newsRepository.getAllNewsLive();
    }

    //接口
    public void insertNews(News... news){
        newsRepository.insertNews(news);
    }

    void updateNews(News... news){
        newsRepository.updateNews(news);
    }

    public void deleteNews(News... news){
        newsRepository.deleteNews(news);
    }


}

package com.example.medicalsystem.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Dao.NewsDao;
import com.example.medicalsystem.DataBase.NewsDatabase;

import java.util.List;

public class NewsRepository {
    private LiveData<List<News>> allNewsLive;
    private NewsDao newsDao;

    public NewsRepository(Context context){
        NewsDatabase newsDatabase = NewsDatabase.getNewsDatabase(context.getApplicationContext());
        newsDao = newsDatabase.getNewsDao();
        allNewsLive = newsDao.getAllNewsLive();
    }

    public LiveData<List<News>> getAllNewsLive() {
        return allNewsLive;
    }


    //接口
    public void insertNews(News... news){
        new InsertAsyncTask(newsDao).execute(news);
    }

    public void updateNews(News... news){
        new UpdateAsyncTask(newsDao).execute(news);
    }

    public void deleteNews(News... news){
        new DeleteAsyncTask(newsDao).execute(news);
    }



    //第2、3参数用于报告进度 报告结果
    static class InsertAsyncTask extends AsyncTask<News, Void , Void> {
        private NewsDao newsDao;

        public InsertAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDao.insertNews(news);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<News, Void , Void>{
        private NewsDao newsDao;

        public UpdateAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDao.updateNews(news);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<News, Void , Void>{
        private NewsDao newsDao;

        public DeleteAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDao.deleteNews(news);
            return null;
        }
    }


}

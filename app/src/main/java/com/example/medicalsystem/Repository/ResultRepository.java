package com.example.medicalsystem.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.medicalsystem.Bean.Result;
import com.example.medicalsystem.Dao.ResultDao;
import com.example.medicalsystem.DataBase.ResultDatabase;


import java.util.List;

public class ResultRepository {
    private LiveData<List<Result>> allResultLive;
    private ResultDao resultDao;

    public ResultRepository(Context context){
        ResultDatabase resultDatabase = ResultDatabase.getResultDatabase(context.getApplicationContext());
        resultDao = resultDatabase.getResultDao();
        allResultLive = resultDao.getAllResultLive();
    }

    public LiveData<List<Result>> getAllResultLive() {
        return allResultLive;
    }


    //接口
    public void insertResult(Result... results){
        new ResultRepository.InsertAsyncTask(resultDao).execute(results);
    }

    public void updateResult(Result... results){
        new ResultRepository.UpdateAsyncTask(resultDao).execute(results);
    }

    public void deleteResult(Result... results){
        new ResultRepository.DeleteAsyncTask(resultDao).execute(results);
    }

    public void deleteAllResults(Result... results){
        new ResultRepository.DeleteAllAsyncTask(resultDao).execute();
    }


    //第2、3参数用于报告进度 报告结果
    static class InsertAsyncTask extends AsyncTask<Result, Void , Void> {
        private ResultDao resultDao;

        public InsertAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.insertResult(results);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Result, Void , Void>{
        private ResultDao resultDao;

        public UpdateAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.updateResult(results);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Result, Void , Void>{
        private ResultDao resultDao;

        public DeleteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.deleteResult(results);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void , Void>{
        private ResultDao resultDao;

        public DeleteAllAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            resultDao.deleteAllResults();
            return null;
        }
    }
}

package com.example.medicalsystem.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.Dao.RecordDao;
import com.example.medicalsystem.DataBase.RecordDatabase;

import java.util.List;

public class RecordRepository {
    private LiveData<List<Record>> allRecordLive;
    private RecordDao recordDao;

    public RecordRepository(Context context){
        RecordDatabase recordDatabase = RecordDatabase.getRecordDatabase(context.getApplicationContext());
        recordDao = recordDatabase.getRecordDao();
        allRecordLive = recordDao.getAllRecordLive();
    }

    public LiveData<List<Record>> getAllRecordLive() {
        return allRecordLive;
    }


    //接口
    public void insertRecord(Record... records){
        new RecordRepository.InsertAsyncTask(recordDao).execute(records);
    }

    public void updateRecord(Record... records){
        new RecordRepository.UpdateAsyncTask(recordDao).execute(records);
    }

    public void deleteRecord(Record... records){
        new RecordRepository.DeleteAsyncTask(recordDao).execute(records);
    }



    //第2、3参数用于报告进度 报告结果
    static class InsertAsyncTask extends AsyncTask<Record, Void , Void> {
        private RecordDao recordDao;

        public InsertAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertRecord(records);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Record, Void , Void>{
        private RecordDao recordDao;

        public UpdateAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateRecord(records);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Record, Void , Void>{
        private RecordDao recordDao;

        public DeleteAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteRecord(records);
            return null;
        }
    }
}

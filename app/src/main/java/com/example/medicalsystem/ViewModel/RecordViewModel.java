package com.example.medicalsystem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medicalsystem.Bean.News;
import com.example.medicalsystem.Bean.Record;
import com.example.medicalsystem.Repository.RecordRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private RecordRepository recordRepository;

    public RecordViewModel(@NonNull Application application) {
        super(application);
        recordRepository = new RecordRepository(application);
    }

    public LiveData<List<Record>> getAllRecordLive() {
        return recordRepository.getAllRecordLive();
    }

    //接口
    public void insertRecord(Record... records){
        recordRepository.insertRecord(records);
    }

    void updateRecord(Record... records){
        recordRepository.updateRecord(records);
    }

    public void deleteRecord(Record... records){
        recordRepository.deleteRecord(records);
    }
}

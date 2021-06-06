package com.example.medicalsystem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.medicalsystem.Bean.Result;
import com.example.medicalsystem.Repository.ResultRepository;

import java.util.List;

public class ResultViewModel extends AndroidViewModel {
    private ResultRepository resultRepository;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        resultRepository = new ResultRepository(application);
    }

    public LiveData<List<Result>> getAllResultLive() {
        return resultRepository.getAllResultLive();
    }

    //接口
    public void insertResult(Result... results){
        resultRepository.insertResult(results);
    }

    public void updateResult(Result... results){
        resultRepository.updateResult(results);
    }

    public void deleteResult(Result... results){
        resultRepository.deleteResult(results);
    }

    public void deleteAllResults(Result... results){
        resultRepository.deleteAllResults();
    }
}

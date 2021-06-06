package com.example.medicalsystem.Bean;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Result {

    @PrimaryKey(autoGenerate =  true)
    private int resultId;

    @ColumnInfo(name = "project_name")
    private String project;

    @ColumnInfo(name = "image_location")
    private int imageLoc;

    @ColumnInfo(name = "result_content")
    private String result;

    @ColumnInfo(name = "result_time")
    private String time;

    public Result(String project, int imageLoc, String result, String time){
        this.project = project;
        this.imageLoc = imageLoc;
        this.result = result;
        this.time = time;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageLoc() {
        return imageLoc;
    }

    public void setImageLoc(int imageLoc) {
        this.imageLoc = imageLoc;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}

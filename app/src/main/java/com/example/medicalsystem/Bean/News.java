package com.example.medicalsystem.Bean;

import android.widget.EditText;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey(autoGenerate =  true)
    public int id;

    @ColumnInfo(name = "news_source_id")
    public int sourceId;

    @ColumnInfo(name = "news_title")
    public String title;

    @ColumnInfo(name = "news_source")
    public String source;

    @ColumnInfo(name = "news_publicTime")
    public String time;

    @ColumnInfo(name = "news_content")
    public String content;

    public News(String title, String source, int sourceId, String time, String content){
        this.title = title;
        this.source = source;
        this.sourceId = sourceId;
        this.time = time;
        this.content = content;
    }


    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }



}

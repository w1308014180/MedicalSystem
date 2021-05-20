package com.example.medicalsystem.Bean;

public class News {
    public String title;
    public String source;
    public String time;

    public News(String title, String source, String time){
        this.title = title;
        this.source = source;
        this.time = time;
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

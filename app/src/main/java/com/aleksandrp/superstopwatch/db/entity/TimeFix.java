package com.aleksandrp.superstopwatch.db.entity;

import java.util.Date;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class TimeFix {

    private long id;
    private String title;
    private Long date;
    private long timeLong;

    public TimeFix(long timeLong) {
        this.date = new Date(System.currentTimeMillis()).getTime();
        this.timeLong = timeLong;
    }

    public TimeFix(String title, long timeLong) {
        this.title = title;
        this.timeLong = timeLong;
        this.date = new Date(System.currentTimeMillis()).getTime();
    }

    public TimeFix(long id, String title, long date, long timeLong) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.timeLong = timeLong;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(long timeLong) {
        this.timeLong = timeLong;
    }
}

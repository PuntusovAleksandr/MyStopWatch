package com.aleksandrp.superstopwatch.entity;

/**
 * Created by Aleksandr on 08.10.2015.
 */
public class Time {
    private String title;
    private String value;
    private int iconId;

    public Time(String title, String value, int iconId) {
        this.title = title;
        this.value = value;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}

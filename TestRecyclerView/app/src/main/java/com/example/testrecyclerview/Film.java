package com.example.testrecyclerview;

public class Film {
    private String title;
    private int year;

    public Film(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}

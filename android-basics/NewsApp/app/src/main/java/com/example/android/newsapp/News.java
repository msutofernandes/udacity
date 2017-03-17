package com.example.android.newsapp;

/**
 * Created by msuto on 18/01/2017.
 */

public class News {

    private String mTitle;
    private String mSection;
    private String mUrl;
    private String mDate;

    public News(String vTitle, String vSection, String url, String vDate){
        mTitle = vTitle;
        mSection = vSection;
        mUrl = url;
        mDate = vDate;
    }

    public String getTitle() { return mTitle; }

    public String getSection() { return mSection; }

    public String getUrl() { return mUrl; }

    public String getDate() { return mDate; }

}

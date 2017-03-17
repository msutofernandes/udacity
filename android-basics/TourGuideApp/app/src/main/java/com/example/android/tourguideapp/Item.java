package com.example.android.tourguideapp;

/**
 * Created by msuto on 27/12/2016.
 */

public class Item {

    private String mItemName;
    private String mItemDescription;
    private String mPrice;
    private int mImageResourceId;

    public Item(String vItemName, String vItemDescription, String vPrice, int imageResourceId){
        mItemName = vItemName;
        mItemDescription = vItemDescription;
        mPrice = vPrice;
        mImageResourceId = imageResourceId;
    }

    public String getItemName(){
        return mItemName;
    }

    public String getItemDescription(){
        return mItemDescription;
    }

    public String getPrice(){
        return mPrice;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
}
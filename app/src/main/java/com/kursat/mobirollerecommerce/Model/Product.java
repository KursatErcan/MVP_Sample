package com.kursat.mobirollerecommerce.Model;

import com.kursat.mobirollerecommerce.util.Constant;

import java.util.HashMap;

public class Product {
    private String key;
    private String title;
    private String description;
    private float price;
    private String category;
    private String date;
    public String getDate() {
        return date;
    }

    public Product(){}

    public Product(String category, String title, String description, float price,String date) {
        this.date = date;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
    }
    public Product(String key,String category, String title, String description, float price, String date) {
        this.key = key;
        this.date = date;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;

    }
    public String getKey() {
        return key;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public float getPrice() { return price; }

    public String getCategory() { return category; }

    public HashMap<String, Object> toHashMap(){
        HashMap<String, Object> mData = new HashMap();
        mData.put(Constant.KEY_CATEGORY,category);
        mData.put(Constant.KEY_TITLE,title);
        mData.put(Constant.KEY_DESCRIPTION,description);
        mData.put(Constant.KEY_PRICE,price);
        mData.put(Constant.KEY_DATE, date);

        return mData;
    }

}

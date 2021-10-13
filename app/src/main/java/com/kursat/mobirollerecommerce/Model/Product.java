package com.kursat.mobirollerecommerce.Model;

import com.kursat.mobirollerecommerce.util.Constant;

import java.util.HashMap;
import java.util.UUID;

public class Product {
    private String id;
    private String title;
    private String description;
    private String price;
    private String category;

    public Product(){}

    public Product(String category, String title, String description, String price) {
        this.id = UUID.randomUUID().toString();;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getPrice() { return price; }

    public String getCategory() { return category; }

    public HashMap<String, Object> toHashMap(){
        HashMap<String, Object> mData = new HashMap();
        mData.put(Constant.KEY_CATEGORY,category);
        mData.put(Constant.KEY_TITLE,title);
        mData.put(Constant.KEY_DESCRIPTION,description);
        mData.put(Constant.KEY_PRICE,price);

        return mData;

    }

    //String uniqueID = UUID.randomUUID().toString();
}

package com.kursat.mobirollerecommerce.Model;

public class Product {
    private String id;
    private String title;
    private String description;
    private String price;
    private String category;

    public Product(String id, String title, String description, String price, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}

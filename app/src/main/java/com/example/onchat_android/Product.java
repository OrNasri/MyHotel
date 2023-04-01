package com.example.onchat_android;

public class Product {
    public String title;
    public String title_price;
    public String description;
    public Integer image;

    public Product(String title, String desc, String titlep, Integer img ){
        this.title = title;
        this.title_price = titlep;
        this.description = desc;
        this.image = img;
    }

    public Integer getImg() {
        return image;
    }

    public String getTitleP() {
        return title_price;
    }
    public String getDesc() {
        return description;
    }
}

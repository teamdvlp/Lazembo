package com.example.phamf.app_selling.Model;

/**
 * Created by phamf on 30-May-17.
 */

public class CartModel {
    int image_product;
    String name_product, price_product;
    int count_product;

    public CartModel(int image_product, String name_product, String price_product, int count_product) {
        this.image_product = image_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.count_product = count_product;
    }

    public int getImage_product() {
        return image_product;
    }

    public void setImage_product(int image_product) {
        this.image_product = image_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public int getCount_product() {
        return count_product;
    }

    public void setCount_product(int count_product) {
        this.count_product = count_product;
    }
}

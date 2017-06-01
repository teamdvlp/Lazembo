package com.example.phamf.app_selling.Model;

/**
 * Created by phamf on 28-May-17.
 */

public class ProductInformationModel {
    String name,price,sale_off_price;
    int product_image;
    boolean state;

    public ProductInformationModel(String name, String price, String sale_off_price, int product_image, boolean state) {
        this.name = name;
        this.price = price;
        this.sale_off_price = sale_off_price;
        this.product_image = product_image;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSale_off_price() {
        return sale_off_price;
    }

    public void setSale_off_price(String sale_off_price) {
        this.sale_off_price = sale_off_price;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

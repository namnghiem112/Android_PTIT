package com.example.btl.model;

public class Product {
    private String id;
    private String urlImg;
    private String productName;
    private String description;
    private String brand;
    private int productPrice;

    private int numProduct = 1;

    public Product() {
    }

    public Product(String id, String urlImg, String productName, String description, String brand, int productPrice, int numProduct) {
        this.id = id;
        this.urlImg = urlImg;
        this.productName = productName;
        this.description = description;
        this.brand = brand;
        this.productPrice = productPrice;
        this.numProduct = numProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }
}

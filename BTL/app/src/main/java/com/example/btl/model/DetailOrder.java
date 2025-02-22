package com.example.btl.model;

public class DetailOrder {
    private String OrderNo;
    private String productName;
    private String urlImg;
    private String status;
    private int productPrice;
    private int numProduct;

    public DetailOrder() {
    }

    public DetailOrder(String productName, String urlImg, String status, int productPrice) {
        this.productName = productName;
        this.urlImg = urlImg;
        this.status = status;
        this.productPrice = productPrice;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

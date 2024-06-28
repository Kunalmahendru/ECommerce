package com.example.ecommerce.Models;

public class MyCartModel {
String productPrice;
String currentTime;
    String currentDate;
    String productName;
    String totalQuantity;
   int  totalPrice;

    public MyCartModel(String productPrice, String currentTime, String currentDate, String productName, String totalQuantity, int totalPrice) {
        this.productPrice = productPrice;
        this.currentTime = currentTime;
        this.currentDate = currentDate;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public MyCartModel() {
    }
}

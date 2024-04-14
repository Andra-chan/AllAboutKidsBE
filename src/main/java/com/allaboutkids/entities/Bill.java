package com.allaboutkids.entities;

public class Bill {

    private String price;
    private String description;
    private String quantity;
    private String address;
    private String city;
    private String county;
    private Parent parent;

    public Bill(){}

    public Bill(String price, String description, String quantity, String address, String city, String county, Parent parent) {
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.address = address;
        this.city = city;
        this.county = county;
        this.parent = parent;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", parent=" + parent +
                '}';
    }
}

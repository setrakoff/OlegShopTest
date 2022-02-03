package com.gmail.setrakovov.objects;

public class ProductCard {
    private String name;
    private Integer price;

    public ProductCard(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

}

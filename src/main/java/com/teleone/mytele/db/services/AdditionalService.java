package com.teleone.mytele.db.services;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String desc;

    AdditionalService() { }
    public AdditionalService(Long id) {
        this.id = id;
    }

    public AdditionalService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }
}

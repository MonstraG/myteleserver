package com.teleone.mytele.db.tarifs;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int priceForConnection;
    private int minutes;
    private int sms;
    private int internet;

    Tariff() { }
    public Tariff(Long id) {
        this.id = id;
    }

    public Tariff(String name) {
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

    public int getPriceForConnection() { return priceForConnection; }

    public void setPriceForConnection(int priceForConnection) { this.priceForConnection = priceForConnection; }

    public int getMinutes() { return minutes; }

    public void setMinutes(int minutes) { this.minutes = minutes; }

    public int getSms() { return sms; }

    public void setSms(int sms) { this.sms = sms; }

    public int getInternet() { return internet; }

    public void setInternet(int internet) { this.internet = internet; }
}

package com.teleone.mytele.db.tariff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int monthlyCharge;
    private int connectionCharge;
    private int minutes;

    private int sms;
    private int internet;

    Tariff() {
    }

    public Tariff(String name, int monthlyCharge, int connectionCharge, int minutes, int sms, int internet) {
        this.name = name;
        this.monthlyCharge = monthlyCharge;
        this.connectionCharge = connectionCharge;
        this.minutes = minutes;
        this.sms = sms;
        this.internet = internet;
    }

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

    public int getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(int monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    public int getConnectionCharge() {
        return connectionCharge;
    }

    public void setConnectionCharge(int connectionCharge) {
        this.connectionCharge = connectionCharge;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }
}

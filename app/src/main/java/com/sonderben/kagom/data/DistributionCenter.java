package com.sonderben.kagom.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

public class DistributionCenter implements Serializable {


    Long id;
    String name;
    private String email;
    private String tel;

    @Override
    public String toString() {
        return "DistributionCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", isInternational=" + isInternational +
                ", schedule='" + schedule + '\'' +
                ", address=" + address +
                '}';
    }

    private boolean isInternational;

     String schedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionCenter that = (DistributionCenter) o;
        return isInternational == that.isInternational && id.equals(that.id) && name.equals(that.name) && Objects.equals(email, that.email) && Objects.equals(tel, that.tel) && Objects.equals(schedule, that.schedule) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, tel, isInternational, schedule, address);
    }

    Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    public DistributionCenter(Long id, String name, String email, String tel, boolean isInternational, String schedule, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.isInternational = isInternational;
        this.schedule = schedule;
        this.address = address;
    }
    public DistributionCenter(){}
}

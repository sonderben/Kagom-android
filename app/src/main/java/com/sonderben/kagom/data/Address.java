package com.sonderben.kagom.data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;




public class Address implements Serializable {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String codePostal;
    private String direction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id) && Objects.equals(country, address.country) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(codePostal, address.codePostal) && Objects.equals(direction, address.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, state, city, codePostal, direction);
    }

    public Address(Long id, String country, String state, String city, String codePostal, String direction) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.city = city;
        this.codePostal = codePostal;
        this.direction = direction;
    }

    public Address(){}

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getDirection() {
        return direction;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

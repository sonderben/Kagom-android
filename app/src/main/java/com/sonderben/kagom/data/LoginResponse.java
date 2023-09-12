package com.sonderben.kagom.data;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String jwt;
    private Customer customer;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "jwt='" + jwt + '\'' +
                ", customer=" + customer +
                '}';
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoginResponse(String jwt, Customer customer) {
        this.jwt = jwt;
        this.customer = customer;
    }
}

package com.sonderben.kagom.data;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private String telephone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && Objects.equals(fullName, customer.fullName) && Objects.equals(email, customer.email) && Objects.equals(telephone, customer.telephone) && Objects.equals(birthday, customer.birthday) && Objects.equals(address, customer.address) && Objects.equals(countryIdentity, customer.countryIdentity) && Objects.equals(distributionCenter, customer.distributionCenter) && Objects.equals(internationalAddresses, customer.internationalAddresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, telephone, birthday, address, countryIdentity, distributionCenter, internationalAddresses);
    }

    private Long birthday;
    private String kmIdentity;
    private Address address;
    private String password;
    private String countryIdentity;



    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", kmIdentity='" + kmIdentity + '\'' +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", distributionCenter=" + distributionCenter +
                ", internationalAddresses=" + internationalAddresses +
                ", points=" + points +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private DistributionCenter distributionCenter,internationalAddresses;

    public DistributionCenter getInternationalAddresses() {
        return internationalAddresses;
    }

    public String getCountryIdentity() {
        return countryIdentity;
    }

    public void setCountryIdentity(String countryIdentity) {
        this.countryIdentity = countryIdentity;
    }

    public Customer(Long id, String fullName, String email, String telephone, Long birthday, String KMIdentity, Address address, DistributionCenter distributionCenter, DistributionCenter internationalAddresses, Long points, String password, String countryIdentity) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.kmIdentity = KMIdentity;
        this.address = address;
        this.distributionCenter = distributionCenter;
        this.internationalAddresses = internationalAddresses;
        this.points = points;
        this.password = password;
        this.countryIdentity = countryIdentity;
    }

    public Customer(){}

    public void setInternationalAddresses(DistributionCenter internationalAddresses) {
        this.internationalAddresses = internationalAddresses;
    }

    private Long points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getKmIdentity() {
        return kmIdentity;
    }

    public void setKmIdentity(String kmIdentity) {
        this.kmIdentity = kmIdentity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    public Long getPoints() {
        return points;
    }



    public Customer(Long id, String fullName, String email, String telephone, Long birthday,
                    String kmIdentity, Address address, String password, String countryIdentity,
                    DistributionCenter distributionCenter, DistributionCenter internationalAddresses, Long points) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.kmIdentity = kmIdentity;
        this.address = address;
        this.password = password;
        this.countryIdentity = countryIdentity;
        this.distributionCenter = distributionCenter;
        this.internationalAddresses = internationalAddresses;
        this.points = points;
    }
}

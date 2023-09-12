package com.sonderben.kagom.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer",
        foreignKeys = {@ForeignKey(entity = AddressEntity.class,
                parentColumns = {"id"}, childColumns = {"address_id"}, onDelete = ForeignKey.CASCADE), @ForeignKey(entity = DistributionEntity.class, parentColumns = "id", childColumns = "distribution_id")}/*, indices = {@Index("address_id")}*/ )
public class CustomerEntity {
    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", countryIdentity='" + countryIdentity + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", kmIdentity='" + kmIdentity + '\'' +
                ", points=" + points +
                ", address_id=" + address_id +
                ", distribution_id=" + distribution_id +
                '}';
    }

    @PrimaryKey
    public Long id;

    /*
    c.getId(),
                c.getCountryIdentity(),
                c.getFullName(),
                c.getEmail(),
                c.getTelephone(),
                c.getBirthday(),
                c.getKmIdentity(),
                addressId,
                distributionId
     */
    public CustomerEntity(Long id, String countryIdentity,
                          String fullName, String email, String telephone,
                          Long birthday, String kmIdentity, Long points,
                          Long address_id, Long distribution_id) {
        this.id = id;
        this.countryIdentity = countryIdentity;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.kmIdentity = kmIdentity;
        this.points = points;
        this.address_id = address_id;
        this.distribution_id = distribution_id;
    }

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

    public Long getAddress_id() {
        return address_id;
    }
    public String countryIdentity;



    public String getCountryIdentity() {
        return countryIdentity;
    }

    public void setCountryIdentity(String countryIdentity) {
        this.countryIdentity = countryIdentity;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public Long getDistribution_id() {
        return distribution_id;
    }

    public void setDistribution_id(Long distribution_id) {
        this.distribution_id = distribution_id;
    }

    public String fullName;
    public String email;
    public String telephone;
    public Long birthday;
    public String kmIdentity;
    public Long points;

    public Long getPoints() {
        return points;
    }



    public Long address_id;
    public Long distribution_id;

}


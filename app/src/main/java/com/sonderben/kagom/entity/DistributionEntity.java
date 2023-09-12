package com.sonderben.kagom.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "distribution",foreignKeys = @ForeignKey(parentColumns = "id",childColumns = "address_id",entity = AddressEntity.class,onDelete = ForeignKey.CASCADE))
public class DistributionEntity {
    @PrimaryKey
    public Long id;
    public String name;

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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public DistributionEntity(Long id, String name, String email, String tel, boolean isInternational, String schedule, Long address_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.isInternational = isInternational;
        this.schedule = schedule;
        this.address_id = address_id;
    }

    public String email;
     public String tel;
     public boolean isInternational;

    public String schedule;
    public Long address_id;
}

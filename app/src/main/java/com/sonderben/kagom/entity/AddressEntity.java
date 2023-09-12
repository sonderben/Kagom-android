package com.sonderben.kagom.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "address")
public class AddressEntity {

    @PrimaryKey
     public Long id;
     public String country;
     public String state;
     public String city;
     public String codePostal;
     public String direction;

 public AddressEntity(Long id, String country, String state, String city, String codePostal, String direction) {
  this.id = id;
  this.country = country;
  this.state = state;
  this.city = city;
  this.codePostal = codePostal;
  this.direction = direction;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getCountry() {
  return country;
 }

 public void setCountry(String country) {
  this.country = country;
 }

 public String getState() {
  return state;
 }

 public void setState(String state) {
  this.state = state;
 }

 public String getCity() {
  return city;
 }

 public void setCity(String city) {
  this.city = city;
 }

 public String getCodePostal() {
  return codePostal;
 }

 public void setCodePostal(String codePostal) {
  this.codePostal = codePostal;
 }

 public String getDirection() {
  return direction;
 }

 public void setDirection(String direction) {
  this.direction = direction;
 }
}

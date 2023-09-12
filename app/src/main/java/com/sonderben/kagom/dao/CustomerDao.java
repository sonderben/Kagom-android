package com.sonderben.kagom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.sonderben.kagom.entity.CustomerEntity;

@Dao
public interface CustomerDao extends BaseDao<CustomerEntity>{
    @Query("Select * from customer inner join address on customer.address_id = address.id where customer.email =:email ")
    LiveData<CustomerEntity> getCurrentCustomerByEmail(String email);

    @Query("Select * from customer inner join address on customer.address_id = address.id where customer.id =:id ")
    LiveData<CustomerEntity> getCurrentCustomerById(Long id);


    @Query("delete  from customer")
    void delete();
}

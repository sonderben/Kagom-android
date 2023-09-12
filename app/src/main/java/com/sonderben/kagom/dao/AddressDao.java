package com.sonderben.kagom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.sonderben.kagom.entity.AddressEntity;

@Dao
public interface AddressDao extends BaseDao<AddressEntity>{
    @Query("select * from address where id =:idAddress")
    LiveData<AddressEntity>  getAddress(long idAddress);
}

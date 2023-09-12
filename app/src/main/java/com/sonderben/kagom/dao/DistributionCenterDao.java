package com.sonderben.kagom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.entity.DistributionEntity;

import java.util.List;

@Dao
public interface DistributionCenterDao extends BaseDao<DistributionEntity> {

    @Query("select * from distribution")
    LiveData< List<DistributionEntity> > findAll();

    @Query("select * from distribution where id = :id")
    LiveData<DistributionEntity> findById(Long id);

}

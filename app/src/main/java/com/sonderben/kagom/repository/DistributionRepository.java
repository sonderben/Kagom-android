package com.sonderben.kagom.repository;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.sonderben.kagom.dao.DistributionCenterDao;
import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.database.KagomDb;
import com.sonderben.kagom.entity.DistributionEntity;

import java.util.List;

public class DistributionRepository {
    private Activity activity;
    private KagomDb kagomDb;
    private DistributionCenterDao dao;

    public DistributionRepository(Activity activity) {
        kagomDb = KagomDb.getInstance(activity.getApplicationContext());
        this.activity = activity;
        //dao = kagomDb.distributionCenterDao();
    }
    public long insert(DistributionEntity distributionCenter){
       return dao.insert( distributionCenter );
    }

    public void delete (DistributionEntity distributionCenter){
        dao.delete( distributionCenter );
    }

    public long update (DistributionEntity distributionCenter){
        return dao.update(distributionCenter);
    }
    public LiveData<List<DistributionEntity>> findAll (DistributionCenter distributionCenter){
        return dao.findAll();
    }

    public LiveData<DistributionEntity> findById (Long id){
        return dao.findById(id);
    }


}

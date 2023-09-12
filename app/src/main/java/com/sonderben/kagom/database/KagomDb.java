package com.sonderben.kagom.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sonderben.kagom.dao.AddressDao;
import com.sonderben.kagom.dao.CustomerDao;
import com.sonderben.kagom.dao.DistributionCenterDao;
import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.DistributionEntity;

@Database(entities = { CustomerEntity.class, DistributionEntity.class, AddressEntity.class}, version = 1, exportSchema = false)
public abstract class KagomDb extends RoomDatabase {

    //public abstract DistributionCenterDao distributionCenterDao();
    public abstract CustomerDao customerDao();
    public abstract AddressDao addressDao();
    public abstract DistributionCenterDao distributionDao();

    public static volatile KagomDb instance;

    public static synchronized KagomDb getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            KagomDb.class, "kagom.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }



}

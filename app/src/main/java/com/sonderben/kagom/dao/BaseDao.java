package com.sonderben.kagom.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface BaseDao <T>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<T> entity);

    @Update
    int update(T entity);

    @Delete
    void delete(T entity);


}

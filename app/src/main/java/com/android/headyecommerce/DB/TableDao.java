package com.android.headyecommerce.DB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.Model.Variant;

import java.util.List;

import retrofit2.http.DELETE;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({VariantConverter.class,TaxConverter.class})
public interface TableDao {

    @Query("SELECT * FROM " + "ProductTable" )
    LiveData<List<ProductTable>> getAllProduct();



    @Insert(onConflict = REPLACE)
     void insert(ProductTable productTable);

    @Query("DELETE FROM  ProductTable Where id=:id")
     void deleteproduct(int id);

    @Query("SELECT COUNT(id) FROM ProductTable ")
    LiveData<Integer> getCount();

    @Query("DELETE FROM ProductTable")
     void delete();

    @Query("SELECT * FROM ProductTable WHERE id= :id")
    ProductTable getItemById(int id);



}

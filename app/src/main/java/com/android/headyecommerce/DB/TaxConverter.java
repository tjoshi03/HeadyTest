package com.android.headyecommerce.DB;

import android.arch.persistence.room.TypeConverter;

import com.android.headyecommerce.Model.Tax;
import com.android.headyecommerce.Model.Variant;
import com.google.gson.Gson;

public class TaxConverter {


    @TypeConverter
    public static Tax toTax(String data) {
        Gson gson=new Gson();
        return data == null ? null : gson.fromJson(data,Tax.class);
    }

    @TypeConverter
    public static String toTaxstring(Tax tax) {
        Gson gson=new Gson();

        return tax == null ? null : gson.toJson(tax);
    }
}

package com.android.headyecommerce.DB;

import android.arch.persistence.room.TypeConverter;

import com.android.headyecommerce.Model.Variant;
import com.google.gson.Gson;

public class VariantConverter {


    @TypeConverter
    public static Variant toVariant(String data) {
        Gson gson=new Gson();
        return data == null ? null : gson.fromJson(data,Variant.class);
    }

    @TypeConverter
    public static String toVariantstring(Variant variant) {
        Gson gson=new Gson();

        return variant == null ? null : gson.toJson(variant);
    }
}

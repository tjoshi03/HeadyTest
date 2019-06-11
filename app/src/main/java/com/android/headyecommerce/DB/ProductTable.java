package com.android.headyecommerce.DB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.android.headyecommerce.Model.Tax;
import com.android.headyecommerce.Model.Variant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity
public class ProductTable {

    @PrimaryKey(autoGenerate = true)
    private int ids;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;

    @TypeConverters(VariantConverter.class)
    @SerializedName("variants")
    @Expose
    private Variant variants;
    @TypeConverters(TaxConverter.class)
    @SerializedName("tax")
    @Expose
    private Tax tax;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductTable() {
    }

    /**
     *
     * @param id
     * @param tax
     * @param name
     * @param variants
     * @param dateAdded
     */
    public ProductTable(Integer id, String name, String dateAdded, Variant variants, Tax tax) {
        super();
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.variants = variants;
        this.tax = tax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Variant getVariants() {
        return variants;
    }

    public void setVariants(Variant variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }


}

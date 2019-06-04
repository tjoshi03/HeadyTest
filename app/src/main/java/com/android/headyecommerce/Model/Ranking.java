package com.android.headyecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ranking {
    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private List<Product_> products = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ranking() {
    }

    /**
     *
     * @param products
     * @param ranking
     */
    public Ranking(String ranking, List<Product_> products) {
        super();
        this.ranking = ranking;
        this.products = products;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<Product_> getProducts() {
        return products;
    }

    public void setProducts(List<Product_> products) {
        this.products = products;
    }
}

package com.android.headyecommerce.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.headyecommerce.Model.Category;
import com.android.headyecommerce.Model.Data;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.Model.Product_;
import com.android.headyecommerce.R;
import com.android.headyecommerce.ViewModel.DataViewModel;
import com.annimon.stream.Collector;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv)
    RecyclerView recyclerView;

    DataViewModel newsViewModel;
    List<Product_>MostViewedProducts=new ArrayList<>();
    List<Product_>MostOrdeRedProducts=new ArrayList<>();

    List<Product_>MostShaRedProducts=new ArrayList<>();
    List<Product>MasterList=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(@Nullable Data newsResponse) {
                for (int i=0;i<newsResponse.getCategories().size();i++){
                    MasterList.addAll(newsResponse.getCategories().get(i).getProducts());
                }
                MostViewedProducts=newsResponse.getRankings().get(0).getProducts();
                MostOrdeRedProducts=newsResponse.getRankings().get(1).getProducts();
                MostShaRedProducts=newsResponse.getRankings().get(2).getProducts();


                List<Product>MostViewedOrder=Stream.of(MasterList).filter(o1 ->Stream.of(MostViewedProducts).anyMatch(o2 ->
                        o2.getId().equals(o1.getId())))
                        .collect(com.annimon.stream.Collectors.toList());

                List<Product>MostOrder=Stream.of(MasterList).filter(o1 ->Stream.of(MostOrdeRedProducts).anyMatch(o2 ->
                        o2.getId().equals(o1.getId())))
                        .collect(com.annimon.stream.Collectors.toList());
                List<Product>MostShared=Stream.of(MasterList).filter(o1 ->Stream.of(MostShaRedProducts).anyMatch(o2 ->
                        o2.getId().equals(o1.getId())))
                        .collect(com.annimon.stream.Collectors.toList());


            }

        });
    }
}

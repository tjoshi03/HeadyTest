package com.android.headyecommerce.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.headyecommerce.Adapter.MosedLiked;
import com.android.headyecommerce.Adapter.MosedShared;
import com.android.headyecommerce.Adapter.MosedViewed;
import com.android.headyecommerce.Model.Category;
import com.android.headyecommerce.Model.Data;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.Model.Product_;
import com.android.headyecommerce.R;
import com.android.headyecommerce.ViewModel.DataViewModel;
import com.annimon.stream.Collector;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_liked)
    RecyclerView recyclerView;
    @BindView(R.id.rv_most)
    RecyclerView recyclerView1;
    @BindView(R.id.rv_shared)
    RecyclerView recyclerView2;
    @BindView(R.id.most)
    TextView textView;
    @BindView(R.id.liked)
    TextView textView1;
    @BindView(R.id.shared)
    TextView textView2;

    DataViewModel newsViewModel;
    List<Product_>MostViewedProducts=new ArrayList<>();
    List<Product_>MostOrdeRedProducts=new ArrayList<>();

    List<Product_>MostShaRedProducts=new ArrayList<>();
    List<Product>MasterList=new ArrayList<>();
    MosedLiked mosedLiked;
    MosedShared mosedShared;
    MosedViewed mosedViewed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
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


                mosedLiked=new MosedLiked(MainActivity.this,MostOrder);
                recyclerView1.setAdapter(mosedLiked);

                mosedShared=new MosedShared(MainActivity.this,MostShared);
                recyclerView2.setAdapter(mosedShared);

                mosedViewed=new MosedViewed(MainActivity.this,MostViewedOrder);
                recyclerView.setAdapter(mosedViewed);

                mosedLiked.Onclick(new MosedLiked.setonclick() {
                    @Override
                    public void onclick(int position) {

                        Product product=MostOrder.get(position);
                            Gson gson=new Gson();
                        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra("data",gson.toJson(product));
                        startActivity(intent);
                    }
                });

                mosedViewed.Onclick(new MosedViewed.setonclick() {
                    @Override
                    public void onclick(int position) {
                        Product product=MostViewedOrder.get(position);
                        Gson gson=new Gson();
                        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra("data",gson.toJson(product));
                        startActivity(intent);
                    }
                });
                mosedShared.Onclick(new MosedShared.setonclick() {
                    @Override
                    public void onclick(int position) {
                        Product product=MostShared.get(position);
                        Gson gson=new Gson();
                        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra("data",gson.toJson(product));
                        startActivity(intent);
                    }
                });

                Intent intent=new Intent(MainActivity.this,AllListActivity.class);

                Gson gson=new Gson();

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        intent.putExtra("data",gson.toJson(MostViewedOrder));
                        startActivity(intent);

                    }
                });
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent.putExtra("data",gson.toJson(MostOrder));
                        startActivity(intent);
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent.putExtra("data",gson.toJson(MostShared));
                        startActivity(intent);
                    }
                });



            }

        });
    }



    public void init(){
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView1.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView2.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_cart:

                startActivity(new Intent(this,CartActivity.class));
                return(true);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu, menu);
        return true;
    }
}

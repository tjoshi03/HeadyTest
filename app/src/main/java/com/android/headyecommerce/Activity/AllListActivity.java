package com.android.headyecommerce.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.headyecommerce.Adapter.CommonAdapter;
import com.android.headyecommerce.Adapter.MosedLiked;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllListActivity extends AppCompatActivity {


    @BindView(R.id.rv)
    RecyclerView rv;

    List<Product> MasterList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        ButterKnife.bind(this);

        rv.setLayoutManager(new GridLayoutManager(this,2));

        Gson gson=new Gson();
        MasterList=gson.fromJson(getIntent().getStringExtra("data"),new TypeToken<List<Product>>(){}.getType());


        CommonAdapter commonAdapter=new CommonAdapter(this,MasterList);
        rv.setAdapter(commonAdapter);

        commonAdapter.Onclick(new CommonAdapter.setonclick() {
            @Override
            public void onclick(int position) {
                Product product=MasterList.get(position);

                Intent intent=new Intent(AllListActivity.this,DetailsActivity.class);
                intent.putExtra("data",gson.toJson(product));
                startActivity(intent);
            }
        });


    }
}

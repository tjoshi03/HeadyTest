package com.android.headyecommerce.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.headyecommerce.Adapter.VariantAdapter;
import com.android.headyecommerce.DB.ProductTable;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.Model.Variant;
import com.android.headyecommerce.R;
import com.android.headyecommerce.ViewModel.TaskListViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.tv_color)
    TextView tv_color;

    @BindView(R.id.tv_size)
    TextView tv_size;

    @BindView(R.id.btn_cart)
    Button btn_cart;

        Gson gson;
        Product product;

        List<Variant>variants=new ArrayList<>();
        int tempposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void init(){
        gson=new Gson();
        product=gson.fromJson(getIntent().getStringExtra("data"),Product.class);
        tempposition=0;
        tv_name.setText(product.getName());
        tv_price.setText("Rs. "+product.getVariants().get(0).getPrice());
        tv_color.setText(product.getVariants().get(0).getColor());
        tv_size.setText(""+product.getVariants().get(0).getSize());

        variants=product.getVariants();
        VariantAdapter variantAdapter=new VariantAdapter(this,variants);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv.setAdapter(variantAdapter);

        variantAdapter.Onclick(position -> {
            tempposition=position;
            tv_price.setText("Rs. "+product.getVariants().get(position).getPrice());
            tv_color.setText(product.getVariants().get(position).getColor());
            tv_size.setText(""+product.getVariants().get(position).getSize());
        });


        btn_cart.setOnClickListener(view -> {
            ProductTable productTable=new ProductTable(product.getId(),product.getName(),product.getDateAdded()
                    ,product.getVariants().get(tempposition),product.getTax());
                   TaskListViewModel taskListViewModel=ViewModelProviders.of(DetailsActivity.this).get( TaskListViewModel.class);
                   taskListViewModel.insertItem(productTable);

            Toast.makeText(DetailsActivity.this,"Added to cart",Toast.LENGTH_SHORT).show();
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_cart:

                startActivity(new Intent(this,CartActivity.class));
                return(true);

            case android.R.id.home:
                onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu, menu);
        return true;
    }
}

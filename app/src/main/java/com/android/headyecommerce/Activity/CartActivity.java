package com.android.headyecommerce.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.headyecommerce.Adapter.CartAdapter;
import com.android.headyecommerce.DB.ProductTable;
import com.android.headyecommerce.R;
import com.android.headyecommerce.ViewModel.TaskListViewModel;
import com.annimon.stream.Stream;
import com.annimon.stream.function.ToIntFunction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {


    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.tv_total)
    TextView tv_total;

    @BindView(R.id.btn_place)
    Button btn_place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        init();
    }

    public void init(){

        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TaskListViewModel viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);

        viewModel.getItem().observe(this, new Observer<List<ProductTable>>() {
            @Override
            public void onChanged(@Nullable List<ProductTable> productTables) {
                CartAdapter cartAdapter=new CartAdapter(CartActivity.this,productTables);
                rv.setAdapter(cartAdapter);


                cartAdapter.Onclick(new CartAdapter.setonclick() {
                    @Override
                    public void onclick(int position) {
                       viewModel.deleteItem(productTables.get(position).getId());
                    }
                });

            int total=    Stream.of(productTables).mapToInt(new ToIntFunction<ProductTable>() {
                    @Override
                    public int applyAsInt(ProductTable productTable) {
                        return productTable.getVariants().getPrice();
                    }
                }).sum();

            tv_total.setText("Total Rs. "+total);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}

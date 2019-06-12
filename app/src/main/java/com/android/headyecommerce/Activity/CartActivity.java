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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

    @BindView(R.id.tv_error)
    TextView tv_error;

    @BindView(R.id.btn_place)
    Button btn_place;

    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;

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

        viewModel.getItem().observe(this, productTables -> {
            CartAdapter cartAdapter=new CartAdapter(CartActivity.this,productTables);
            rv.setAdapter(cartAdapter);


            cartAdapter.Onclick(position -> viewModel.deleteItem(productTables.get(position).getId()));

        int total=    Stream.of(productTables).mapToInt(productTable -> productTable.getVariants().getPrice()).sum();

        tv_total.setText("Total Rs. "+total);

        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,CheckOutActivity.class));
            }
        });

        if (productTables.size()>0){
            rv.setVisibility(View.VISIBLE);
            ll_bottom.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.GONE);
        }
        else {
            rv.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.GONE);
            tv_error.setVisibility(View.VISIBLE);
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

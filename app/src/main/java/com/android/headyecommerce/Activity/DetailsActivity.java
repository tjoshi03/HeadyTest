package com.android.headyecommerce.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.android.headyecommerce.Adapter.VariantAdapter;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.Model.Variant;
import com.android.headyecommerce.R;
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

    @BindView(R.id.btn_buy)
    Button btn_buy;

    @BindView(R.id.tv_color)
    TextView tv_color;

    @BindView(R.id.tv_size)
    TextView tv_size;



        Gson gson;
        Product product;

        List<Variant>variants=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        init();

    }

    public void init(){
        gson=new Gson();
        product=gson.fromJson(getIntent().getStringExtra("data"),Product.class);

        tv_name.setText(product.getName());
        tv_price.setText("Rs. "+product.getVariants().get(0).getPrice());
        tv_color.setText(product.getVariants().get(0).getColor());
        tv_size.setText(""+product.getVariants().get(0).getSize());

        variants=product.getVariants();
        VariantAdapter variantAdapter=new VariantAdapter(this,variants);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv.setAdapter(variantAdapter);

        variantAdapter.Onclick(new VariantAdapter.setonclick() {
            @Override
            public void onclick(int position) {
                tv_price.setText("Rs. "+product.getVariants().get(position).getPrice());
                tv_color.setText(product.getVariants().get(position).getColor());
                tv_size.setText(""+product.getVariants().get(position).getSize());
            }
        });
    }
}

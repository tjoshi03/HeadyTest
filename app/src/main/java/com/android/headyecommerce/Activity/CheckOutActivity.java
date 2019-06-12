package com.android.headyecommerce.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.headyecommerce.DB.ProductTable;
import com.android.headyecommerce.R;
import com.android.headyecommerce.ViewModel.TaskListViewModel;
import com.annimon.stream.Stream;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends AppCompatActivity {


    @BindView(R.id.tv_item)
    TextView tv_item;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.confirm_order)
    Button confirm_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void init(){

        TaskListViewModel viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);


        viewModel.getItem().observe(this, productTables -> {
            int total=    Stream.of(productTables).mapToInt(productTable -> productTable.getVariants().getPrice()).sum();
            tv_item.setText(""+productTables.size());
            tv_price.setText("Rs. "+total);
        });



        confirm_order.setOnClickListener(view -> {
            viewModel.delete();
            alert();

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void alert(){

        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Thank you for shopping with us.")
                .setCancelable(false)
                .setPositiveButton("ok", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }).show();
    }
}

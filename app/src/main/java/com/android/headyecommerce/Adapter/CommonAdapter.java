package com.android.headyecommerce.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.R;

import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder>{



    Context context;
    List<Product>products;
    setonclick setonclick;
    public CommonAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.adpter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(products.get(i).getName());
        viewHolder.cv_product.setOnClickListener(view -> setonclick.onclick(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        CardView cv_product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_product=itemView.findViewById(R.id.cv_product);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
    public interface setonclick{
        void onclick(int position);
    }
    public void Onclick(setonclick setonclick){
        this.setonclick=setonclick;
    }
}

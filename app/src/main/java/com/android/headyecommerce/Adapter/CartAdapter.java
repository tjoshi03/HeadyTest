package com.android.headyecommerce.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.headyecommerce.DB.ProductTable;
import com.android.headyecommerce.Model.Product;
import com.android.headyecommerce.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{



    Context context;
    List<ProductTable>products;
    setonclick setonclick;
    public CartAdapter(Context context, List<ProductTable> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.cartadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(products.get(i).getName()+" ("+products.get(i).getVariants().getColor()+" )");
        viewHolder.tv_size.setText("Size: "+products.get(i).getVariants().getSize());
        viewHolder.tv_price.setText("Rs."+products.get(i).getVariants().getPrice());
        viewHolder.tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setonclick.onclick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_remove,tv_size,tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.tv_name);
            tv_remove=itemView.findViewById(R.id.tv_remove);
            tv_size=itemView.findViewById(R.id.tv_size);
            tv_price=itemView.findViewById(R.id.tv_price);
        }
    }
    public interface setonclick{
        void onclick(int position);
    }
    public void Onclick(setonclick setonclick){
        this.setonclick=setonclick;
    }
}

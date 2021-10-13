package com.kursat.mobirollerecommerce.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>{
    public ArrayList<Product> products;


    public ProductRecyclerAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_product,parent,false);
        ViewHolder pHolder = new ViewHolder(view);

        return pHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = products.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView price;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = (TextView) itemView.findViewById(R.id.tv_itemProductTitle);
            price = (TextView) itemView.findViewById(R.id.tv_itemProductPrice);
            itemView.setOnClickListener(this);

        }

        //post

        @Override
        public void onClick(View view) {
             }
    }

    public interface onProductListener{
        void onPlayerUpdateClick(int position);
        void onPlayerDeleteClick(int position);
    }
}

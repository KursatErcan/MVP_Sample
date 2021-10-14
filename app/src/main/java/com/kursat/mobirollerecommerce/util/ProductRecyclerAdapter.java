package com.kursat.mobirollerecommerce.util;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;

import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>{
    public ArrayList<Product> products;
    public onProductListener listener;

    public ProductRecyclerAdapter(ArrayList<Product> products, onProductListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = products.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(String.valueOf(item.getPrice())+" TL");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView price;
        public Button updateBtn;
        public Button deleteBtn;
        public onProductListener pListener;

        public ViewHolder(@NonNull View itemView, onProductListener listener) {
            super(itemView);
            this.pListener = listener;
            title = (TextView) itemView.findViewById(R.id.tv_itemProductTitle);
            price = (TextView) itemView.findViewById(R.id.tv_itemProductPrice);
            updateBtn = itemView.findViewById(R.id.tv_updateProduct);
            deleteBtn = itemView.findViewById(R.id.tv_deleteProduct);
            CardView productItem = itemView.findViewById(R.id.product_item);

            updateBtn.setOnClickListener(this);
            deleteBtn.setOnClickListener(this);
            productItem.setOnClickListener(this);

        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.product_item:
                    Log.e("recyclerAdapter: ", "item clicked");
                    pListener.onProductItemClick(getAdapterPosition());
                    break;
                case R.id.tv_updateProduct:
                    Log.e("recyclerAdapter: ", "update clicked");
                    pListener.onProductUpdateClick(getAdapterPosition());
                    break;

                case R.id.tv_deleteProduct:
                    Log.e("recyclerAdapter: ", "delete clicked");
                    pListener.onProductDeleteClick(getAdapterPosition());
                    break;

            }
        }
    }

    public interface onProductListener{
        void onProductUpdateClick(int position);
        void onProductDeleteClick(int position);
        void onProductItemClick(int position);
    }
}

package com.kursat.mobirollerecommerce.ProductsList;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;
import com.kursat.mobirollerecommerce.util.ProductRecyclerAdapter;

import java.util.ArrayList;

public class ProductsListActivity extends AppCompatActivity implements ProductsListActivityContract.View{
    RecyclerView recyclerView;
    ProductRecyclerAdapter recyclerAdapter;
    View mView;
    ProductsListActivityPresenter presenter;
    public ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        initView();
        presenter.fetchProducts();
    }

    private void initView(){
        recyclerView = findViewById(R.id.rv_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mView = findViewById(R.id.mainView);
        presenter = new ProductsListActivityPresenter(this);
    }


    @Override
    public void onProductRead(ArrayList<Product> products) {
        this.products=products;
        recyclerAdapter = new ProductRecyclerAdapter(products);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onProductUpdate(Product player) {

    }

    @Override
    public void onProductDelete(Product player) {

    }

    @Override
    public void fetchFailure() {
        Snackbar.make(mView,getResources().getString(R.string.fetch_error),Snackbar.LENGTH_SHORT).show();

    }
}
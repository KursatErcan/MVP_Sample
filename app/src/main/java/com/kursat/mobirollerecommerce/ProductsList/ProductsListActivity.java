package com.kursat.mobirollerecommerce.ProductsList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;
import com.kursat.mobirollerecommerce.util.ProductRecyclerAdapter;

import java.util.ArrayList;

public class ProductsListActivity extends AppCompatActivity implements ProductsListActivityContract.View,
        UpdateProductDialog.UpdateProductDialogListener,
        ProductRecyclerAdapter.onProductListener, SortByDialog.SortByClickListener{
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
        showProductSortByDialog();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        products.clear();

        this.finish();
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
        recyclerAdapter = new ProductRecyclerAdapter(products,this);
        recyclerView.clearOnChildAttachStateChangeListeners();
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onProductUpdate(Product product,int index) {
        products.set(index,product);
        recyclerAdapter.notifyItemChanged(index);
        //Snackbar.make(mView,getResources().getString(R.string.deleted_product)+": "+product.getTitle(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onProductDelete(Product product,int index) {
        products.remove(index);
        recyclerAdapter.notifyItemRemoved(index);
        //Snackbar.make(mView,getResources().getString(R.string.updated_product)+": "+ product.getTitle(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Snackbar.make(findViewById(android.R.id.content),getResources().getString(R.string.fetch_error),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void updateProduct(Product product) {
        presenter.updateProduct(product);
    }

    @Override
    public void onProductUpdateClick(int position) {
        Product product= products.get(position);
        UpdateProductDialog updateProductDialog = new UpdateProductDialog(product);
        updateProductDialog.show(getSupportFragmentManager(),"updatedialog");
    }

    @Override
    public void onProductDeleteClick(int position) {
        Product product = products.get(position);
        Log.e("onProductDeleteClick: ", String.valueOf(product.getPrice()));
        presenter.deleteProduct(product);
    }

    @Override
    public void onProductItemClick(int position) {
        Product product= products.get(position);
        ProductDetailDialog productDetailDialog = new ProductDetailDialog(product);
        productDetailDialog.show(getSupportFragmentManager(),"update dialog");
    }
    void showProductSortByDialog() {
        SortByDialog sortByDialog = new SortByDialog();
        sortByDialog.show(getSupportFragmentManager(),"sortBy dialog");
    }

    @Override
    public void sortByClick(String sortType) {
        Log.e("onSortBy: ", String.valueOf(sortType));
        presenter.fetchProducts(sortType);
    }
}
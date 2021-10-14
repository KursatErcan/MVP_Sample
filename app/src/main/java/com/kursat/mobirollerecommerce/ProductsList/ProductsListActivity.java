package com.kursat.mobirollerecommerce.ProductsList;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;
import com.kursat.mobirollerecommerce.util.Constant;
import com.kursat.mobirollerecommerce.util.ProductRecyclerAdapter;

import java.util.ArrayList;

public class ProductsListActivity extends AppCompatActivity implements ProductsListActivityContract.View,
        UpdateProductDialog.UpdateProductDialogListener,ProductRecyclerAdapter.onProductListener{
    RecyclerView recyclerView;
    ProductRecyclerAdapter recyclerAdapter;
    View mView;
    ProductsListActivityPresenter presenter;
    public ArrayList<Product> products;
    Spinner spinner;
    String sortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        initView();
        presenter.fetchProducts(sortType);
    }

    private void initView(){
        recyclerView = findViewById(R.id.rv_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mView = findViewById(R.id.mainView);
        presenter = new ProductsListActivityPresenter(this);
        sortType= Constant.KEY_PRICE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_sort_spinner_menu,menu);
        MenuItem item = menu.findItem(R.id.sortSpinner);
        spinner = (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_list_item_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortType = adapterView.getSelectedItem().toString();
                //presenter.refreshRecyclerView(sortType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return true;
    }

    @Override
    public void onProductRead(ArrayList<Product> products) {
        this.products=products;
        //if(sortType=="Cheapest" || sortType=="Oldest" ) Collections.reverse(products);
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
    public void onProductsRefreshed(ArrayList<Product> products) {
        this.products=products;
        recyclerAdapter = new ProductRecyclerAdapter(products,this);
        recyclerView.clearOnChildAttachStateChangeListeners();
        recyclerView.setAdapter(recyclerAdapter);
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
        productDetailDialog.show(getSupportFragmentManager(),"updatedialog");
    }
}
package com.kursat.mobirollerecommerce.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.kursat.mobirollerecommerce.AddProduct.AddProductActivity;
import com.kursat.mobirollerecommerce.ProductsList.ProductsListActivity;
import com.kursat.mobirollerecommerce.R;

public class MainPresenterImpl implements MainPresenter {
    private final MainView view;
    public MainPresenterImpl(MainView view) {this.view = view;}
    @Override
    public void pageRoute(Context context, Button button) {
        Intent intent = null;
        if(button.getId() == R.id.btn_addProduct){
            intent = new Intent(context, AddProductActivity.class);
        }
        else if(button.getId() == R.id.btn_showProducts){
            intent = new Intent(context, ProductsListActivity.class);
        }

        if(intent != null) { view.route(intent); }
    }
}

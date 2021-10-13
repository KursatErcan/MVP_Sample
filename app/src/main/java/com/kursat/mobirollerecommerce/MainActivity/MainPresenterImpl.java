package com.kursat.mobirollerecommerce.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.kursat.mobirollerecommerce.AddProduct.AddProduct;
import com.kursat.mobirollerecommerce.Products;
import com.kursat.mobirollerecommerce.R;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    public MainPresenterImpl(MainView mainView){this.mainView = mainView;}
    @Override
    public void pageRoute(Context context, Button button) {
        Intent intent=null;
        if(button.getId()== R.id.btn_addProduct){
            intent = new Intent(context, AddProduct.class);
        }
        else if(button.getId()==R.id.btn_showProducts){
            intent = new Intent(context, Products.class);
        }

        if(intent != null) { mainView.route(intent); }
    }
}

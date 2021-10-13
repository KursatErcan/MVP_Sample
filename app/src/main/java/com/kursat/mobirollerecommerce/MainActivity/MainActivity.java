package com.kursat.mobirollerecommerce.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.kursat.mobirollerecommerce.R;

public class MainActivity extends AppCompatActivity implements MainView{
    Button btn_addProduct,btn_showProducts;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btn_addProduct.setOnClickListener(view -> presenter.pageRoute(this.getApplicationContext(),btn_addProduct));
        btn_showProducts.setOnClickListener(view -> presenter.pageRoute(this.getApplicationContext(),btn_showProducts));

    }
    private void initView(){
        btn_addProduct =findViewById(R.id.btn_addProduct);
        btn_showProducts =findViewById(R.id.btn_showProducts);
        presenter = new MainPresenterImpl(this);
    }


    @Override
    public void route(Intent intent) {
        startActivity(intent);
    }
}
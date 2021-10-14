package com.kursat.mobirollerecommerce.AddProduct;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.kursat.mobirollerecommerce.R;
import com.kursat.mobirollerecommerce.util.Categories;

public class AddProductActivity extends AppCompatActivity implements AddProductActivityContract.View {
    EditText edtTxt_title,edtTxt_explanation,edtTxt_price;
    Button btn_push;
    Spinner spinner_category;
    AddProductActivityPresenter presenter;
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        initView();
        btn_push.setOnClickListener(view ->
                presenter.push(spinner_category.getSelectedItem().toString(),
                        edtTxt_title.getText().toString(),
                edtTxt_explanation.getText().toString(),
                edtTxt_price.getText().toString()));
    }
    private void initView(){
        edtTxt_title =findViewById(R.id.edtTxt_title);
        edtTxt_explanation =findViewById(R.id.edtTxt_description);
        edtTxt_price =findViewById(R.id.edtTxt_price);
        btn_push =findViewById(R.id.btn_push);
        spinner_category=findViewById(R.id.spinner_category);
        mView = findViewById(R.id.addProductView);

        ArrayAdapter<String> dataAdapterForCategories = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Categories.titles);
        dataAdapterForCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(dataAdapterForCategories);

        presenter = new AddProductActivityPresenter(this);
    }

    @Override
    public void showInputError() {
        Snackbar.make(mView,getResources().getString(R.string.input_not_valid),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void pushSuccess() {
        Snackbar.make(mView,getResources().getString(R.string.input_succes),Snackbar.LENGTH_SHORT).show();
        edtTxt_title.setText("");
        edtTxt_explanation.setText("");
        edtTxt_price.setText("");
    }
}
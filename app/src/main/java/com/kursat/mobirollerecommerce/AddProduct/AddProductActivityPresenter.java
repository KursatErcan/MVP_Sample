package com.kursat.mobirollerecommerce.AddProduct;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.util.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductActivityPresenter implements AddProductActivityContract.Presenter, AddProductActivityContract.onOperationListener{
    private final AddProductActivityContract.View view;
    private final AddProductActivityContract.Interactor interactor;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child(Constant.DB_NAME);
    public AddProductActivityPresenter(AddProductActivityContract.View addProductActivityView){
        this.view =addProductActivityView;
        interactor = (AddProductActivityContract.Interactor) new AddProductActivityInteractor(this);
    }

    @Override
    public void push(String category, String title, String description, String price) {
        if(TextUtils.isEmpty(category) || TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(price)){
            onError();
        }
        else{
            @SuppressLint("SimpleDateFormat")
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String strDate = dateFormat.format(date);

            Product product = new Product(myRef.push().getKey(),
                    category,
                    title,
                    description,
                    Float.parseFloat(price),
                    strDate);

            Log.e("date: ", strDate);

            interactor.createProduct(myRef,product);
        }
    }

    @Override
    public void onSuccess() { view.pushSuccess(); }

    @Override
    public void onError() { view.showInputError();}
}

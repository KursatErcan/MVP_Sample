package com.kursat.mobirollerecommerce.AddProduct;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.util.Constant;

import java.sql.Timestamp;
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
            Product product = new Product(category,title,description,Float.parseFloat(price),new Date());
            interactor.createProduct(myRef,product);
        }
    }

    @Override
    public void onSuccess() { view.pushSuccess(); }

    @Override
    public void onFailure() { view.pushFailure(); }

    @Override
    public void onError() { view.showInputError();}
}

package com.kursat.mobirollerecommerce.AddProduct;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.util.Constant;

public class AddProductActivityInteractor implements AddProductActivityContract.Interactor{
    AddProductActivityContract.onOperationListener listener;
    public AddProductActivityInteractor(AddProductActivityContract.onOperationListener listener){
        this.listener = listener;
    }

    @Override
    public void createProduct(DatabaseReference dbRef, Product product) {
        dbRef.child(product.getId()).setValue(product.toHashMap());
        dbRef.push();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError();
                Log.e("onCancelled: ", error.toString());

            }
        });
    }

    @Override
    public void updateproduct() {

    }

    @Override
    public void deleteproduct() {

    }
}

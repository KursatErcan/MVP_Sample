package com.kursat.mobirollerecommerce.AddProduct;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

public class AddProductActivityInteractor implements AddProductActivityContract.Interactor{
    AddProductActivityContract.onOperationListener listener;
    public AddProductActivityInteractor(AddProductActivityContract.onOperationListener listener){
        this.listener = listener;
    }

    @Override
    public void createProduct(DatabaseReference dbRef, Product product) {
        dbRef.child(product.getKey()).setValue(product).addOnCompleteListener(task -> {
            if(task.isSuccessful()) { listener.onSuccess(); }
            else {
                listener.onError();
                Log.e("onCancelled: ", task.toString());
            }
        });
    }
}

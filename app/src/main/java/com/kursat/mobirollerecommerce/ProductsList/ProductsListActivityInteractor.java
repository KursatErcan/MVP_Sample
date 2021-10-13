package com.kursat.mobirollerecommerce.ProductsList;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

import java.util.ArrayList;

public class ProductsListActivityInteractor implements ProductsListActivityContract.Interactor {
    private ArrayList<Product> products = new ArrayList<>();
    ProductsListActivityContract.onOperationListener listener;

    ProductsListActivityInteractor(ProductsListActivityContract.onOperationListener listener){this.listener = listener;}
    @Override
    public void readProducts(DatabaseReference dbRef) {
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                products.add(product);
                listener.onRead(products);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                listener.onUpdate(product);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                listener.onDelete(product);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
                Log.e("onCancelled: ", error.toString());
            }
        });

    }

    @Override
    public void updateProduct(DatabaseReference dbRef) {

    }

    @Override
    public void deleteProduct(DatabaseReference dbRef) {

    }
}

package com.kursat.mobirollerecommerce.ProductsList;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.util.Constant;

import java.util.ArrayList;
import java.util.Collections;

public class ProductsListActivityInteractor implements ProductsListActivityContract.Interactor {
    private final ArrayList<Product> products = new ArrayList<>();
    ProductsListActivityContract.onOperationListener listener;

    ProductsListActivityInteractor(ProductsListActivityContract.onOperationListener listener){this.listener = listener;}
    @Override
    public void readProducts(DatabaseReference dbRef,String sortType) {

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("onChildAdded: ", String.valueOf(snapshot));
                Product product = snapshot.getValue(Product.class);
                products.add(product);
                sortProductsList(sortType);
                //Collections.reverse(products);
                listener.onRead(products);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                listener.onUpdate(product,getIndex(product));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                listener.onDelete(product,getIndex(product));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void updateProduct(DatabaseReference dbRef, Product product) {
        dbRef.child(product.getKey()).setValue(product)
                .addOnCompleteListener((OnCompleteListener) task -> {
            if(!task.isSuccessful()){ listener.onFailure(); }
        });
    }

    @Override
    public void deleteProduct(DatabaseReference dbRef, Product product) {
        dbRef.child(product.getKey()).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //listener.onDeleteSuccess();
            }
            Log.e("onFailed: ", task.toString());
        });
    }
    @Override
    public void sortProductsList(String sortType) {
        if(sortType=="Cheapest" || sortType=="Oldest" ) Collections.reverse(products);
        listener.onRead(products);
    }
    /*
    @Override
    public void sortProductsList(DatabaseReference dbRef, String sortType) {
        dbRef.orderByChild(orderBy(sortType)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    if(sortType=="Cheapest" || sortType=="Oldest" ) Collections.reverse(products);
                    listener.onRead(products);
                }else {
                    Log.e("onCancelled: ", String.valueOf(task.getResult().getValue()));
                    listener.onFailure();
                }
            }
        });
    }*/
    private String orderBy(String sortType){
        String order = Constant.KEY_PRICE;
        switch (sortType){
            case "Cheapest":
            case "Expensive":
                order = Constant.KEY_PRICE;
                break;
            case "Newest":
            case "Oldest":
                order = Constant.KEY_DATE;
                break;
        }
        return order;
    }

    public int getIndex(Product product)
    {
        int index = 0;

        for (Product countPlayer: products)
        {
            if(countPlayer.getKey().trim().equals(product.getKey()))
            {
                break;
            }
            index++;
        }

        return index;
    }}

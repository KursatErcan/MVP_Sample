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

public class AddProductPresenterImpl implements AddProductPresenter{
    private final AddProductView view;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public AddProductPresenterImpl(AddProductView addProductView){ this.view =addProductView; }

    @Override
    public void push(String category,String title, String description, String price) {
        if(TextUtils.isEmpty(category) || TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(price)){
            view.showInputError();
        }
        else{
            Product product = new Product(category,title,description,price);

            myRef.child(Constant.DB_NAME).child(product.getId()).setValue(product.toHashMap());
            myRef.push();

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    view.pushSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    view.pushError();
                    Log.e("onCancelled: ", error.toString());

                }
            });


        }
    }

}

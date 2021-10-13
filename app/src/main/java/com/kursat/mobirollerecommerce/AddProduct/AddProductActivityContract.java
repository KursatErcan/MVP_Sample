package com.kursat.mobirollerecommerce.AddProduct;

import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

import java.util.ArrayList;

public class AddProductActivityContract {
    interface View {
        void showInputError();
        void pushSuccess();
        void pushFailure();
    }

    interface Presenter {
        void push(String category, String title, String description, String price);
    }

    public interface Interactor {
        void createProduct(DatabaseReference dbRef, Product product);
        void updateproduct();
        void deleteproduct();
    }
    interface onOperationListener{
        void onSuccess();
        void onFailure();
        void onError();

    }

}

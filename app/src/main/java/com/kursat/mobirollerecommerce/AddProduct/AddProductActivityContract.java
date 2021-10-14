package com.kursat.mobirollerecommerce.AddProduct;

import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

public class AddProductActivityContract {
    interface View {
        void showInputError();
        void pushSuccess();
    }

    interface Presenter {
        void push(String category, String title, String description, String price);
    }

    public interface Interactor {
        void createProduct(DatabaseReference dbRef, Product product);
    }
    interface onOperationListener{
        void onSuccess();
        void onError();

    }

}

package com.kursat.mobirollerecommerce.ProductsList;

import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

import java.util.ArrayList;

public class ProductsListActivityContract {
    interface View {
        void onProductRead(ArrayList<Product>players);
        void onProductUpdate(Product player);
        void onProductDelete(Product player);

        void fetchFailure();
    }
    interface Presenter {
        void fetchProducts();
    }

    interface Interactor {
        void readProducts(DatabaseReference dbRef);
        void updateProduct(DatabaseReference dbRef);
        void deleteProduct(DatabaseReference dbRef);
    }
    interface onOperationListener{
        void onRead(ArrayList<Product> products);
        void onUpdate(Product product);
        void onDelete(Product product);
        void onFailure();

    }
}

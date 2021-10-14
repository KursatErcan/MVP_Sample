package com.kursat.mobirollerecommerce.ProductsList;

import com.google.firebase.database.DatabaseReference;
import com.kursat.mobirollerecommerce.Model.Product;

import java.util.ArrayList;

public class ProductsListActivityContract {
    interface View {
        void onProductRead(ArrayList<Product>products);
        void onProductUpdate(Product product, int index);
        void onProductDelete(Product product,int index);

        void onProductsRefreshed(ArrayList<Product>products);
        void onFailure();
    }
    interface Presenter {
        void updateProduct(Product product);
        void deleteProduct(Product product);
        void fetchProducts(String sortType);
        void refreshRecyclerView(String sortType);

    }

    interface Interactor {
        void readProducts(DatabaseReference dbRef,String sortType);
        void updateProduct(DatabaseReference dbRef,Product product);
        void deleteProduct(DatabaseReference dbRef,Product product);
        void sortProductsList(String sortType);
    }
    interface onOperationListener{
        void onRefreshed(ArrayList<Product> products);
        void onRead(ArrayList<Product> products);
        void onUpdate(Product product, int index);
        void onDelete(Product product,int index);
        void onFailure();


    }
}

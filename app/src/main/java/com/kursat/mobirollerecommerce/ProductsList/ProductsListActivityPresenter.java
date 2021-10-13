package com.kursat.mobirollerecommerce.ProductsList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.util.Constant;

import java.util.ArrayList;

public class ProductsListActivityPresenter implements ProductsListActivityContract.Presenter, ProductsListActivityContract.onOperationListener{
    private final ProductsListActivityContract.View view;
    private final ProductsListActivityInteractor interactor;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child(Constant.DB_NAME);
    ProductsListActivityPresenter(ProductsListActivityContract.View view){
        this.view = view;
        interactor = new ProductsListActivityInteractor(this);

    }

    @Override
    public void fetchProducts() {
        interactor.readProducts(myRef);
    }

    @Override
    public void onRead(ArrayList<Product> products) {
        view.onProductRead(products);
    }

    @Override
    public void onUpdate(Product product) {
        view.onProductUpdate(product);
    }

    @Override
    public void onDelete(Product product) {
        view.onProductUpdate(product);
    }
    @Override
    public void onFailure() { view.fetchFailure(); }
}

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
    public void updateProduct(Product product) {
        interactor.updateProduct(myRef,product);
    }

    @Override
    public void deleteProduct(Product product) {
        interactor.deleteProduct(myRef,product);
    }

    @Override
    public void fetchProducts(String sortType) {
        interactor.readProducts(myRef,sortType);
    }

    @Override
    public void refreshRecyclerView(String sortType) {
        interactor.sortProductsList(sortType);
    }

    @Override
    public void onRefreshed(ArrayList<Product> products) {
        view.onProductsRefreshed(products);
    }

    @Override
    public void onRead(ArrayList<Product> products) {
        view.onProductRead(products);
    }

    @Override
    public void onUpdate(Product product,int index) {
        view.onProductUpdate(product,index);
    }

    @Override
    public void onDelete(Product product,int index) {
        view.onProductDelete(product,index);
    }
    @Override
    public void onFailure() { view.onFailure(); }
}

package com.kursat.mobirollerecommerce.ProductsList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDetailDialog extends AppCompatDialogFragment {
    Product product;
    View view;
    AlertDialog.Builder builder;
    public TextView pTitle,pDescription,pPrice,pCategory,pDate;
    public ProductDetailDialog(Product product) { this.product = product; }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_detail_product_dialog, null);
        builder.setView(view);
        //builder.setTitle("Product Details");

        builder.setCancelable(true);

        initView();


        return builder.create();
    }
    public void initView(){
        pTitle = view.findViewById(R.id.tv_productTitle);
        pDescription = (TextView)view.findViewById(R.id.tv_productDescription);
        pPrice = (TextView)view.findViewById(R.id.tv_productPrice);
        pCategory = (TextView) view.findViewById(R.id.tv_productCategory);
        pDate = (TextView) view.findViewById(R.id.tv_productDate);


        pTitle.setText(product.getTitle());
        pDescription.setText(product.getDescription());
        pPrice.setText(String.valueOf(product.getPrice())+" TL");
        pCategory.setText(String.valueOf(product.getCategory()));

        Date date = new Date(product.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        pDate.setText(strDate);

    }
}

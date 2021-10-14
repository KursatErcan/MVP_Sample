package com.kursat.mobirollerecommerce.ProductsList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.kursat.mobirollerecommerce.Model.Product;
import com.kursat.mobirollerecommerce.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateProductDialog extends AppCompatDialogFragment {
    public EditText pTitle,pDescription,pPrice;
    public Button saveButton;
    public TextView pCategory;
    public Product product;
    View view;
    AlertDialog.Builder builder;
    public UpdateProductDialog.UpdateProductDialogListener listener;

    public UpdateProductDialog(Product product) {
        this.product = product;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_update_product_dialog, null);
        builder.setView(view);
        builder.setCancelable(true);

        initView();

        saveButton.setOnClickListener(view -> {
            String category = product.getCategory();
            String title = pTitle.getText().toString();
            String description = pDescription.getText().toString();
            String price = pPrice.getText().toString();

            if(TextUtils.isEmpty(category) || TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(price)){
                return;
            }
            else{
                @SuppressLint("SimpleDateFormat")
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date);
                Product mProduct = new Product(product.getKey(),category,title,description,Float.parseFloat(price), strDate);
                listener.updateProduct(mProduct);
                dismiss();
            }
        });

        return builder.create();
    }
    public void initView(){
        pTitle = view.findViewById(R.id.edtTxt_updateTitle);
        pDescription = (EditText)view.findViewById(R.id.edtTxt_updateDescription);
        pPrice = (EditText)view.findViewById(R.id.edtTxt_updatePrice);
        pCategory = view.findViewById(R.id.tv_updateCategory);
        saveButton = (Button)view.findViewById(R.id.btn_save);
        //categorySpinner = view.findViewById(R.id.spinner_category);

        pTitle.setText(product.getTitle());
        pDescription.setText(product.getDescription());
        pPrice.setText(String.valueOf(product.getPrice()));
        pCategory.setText(String.valueOf(product.getCategory()));


    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (UpdateProductDialog.UpdateProductDialogListener) context;
    }

    public interface UpdateProductDialogListener{
        void updateProduct(Product product);
    }

}


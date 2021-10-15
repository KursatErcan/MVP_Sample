package com.kursat.mobirollerecommerce.ProductsList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.kursat.mobirollerecommerce.R;
import com.kursat.mobirollerecommerce.util.Constant;

public class SortByDialog extends AppCompatDialogFragment {
    public Button btn_sortByPrice,btn_sortByDate,btn_sortByNothing;
    public SortByDialog(){}
    View view;
    public SortByDialog.SortByClickListener listener;
    String sortType = Constant.SORT_BY_NOTHING;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_sortby_dialog, null);
        builder.setView(view);
        builder.setTitle("SortBy: ");
        builder.setCancelable(false);

        initView();

        btn_sortByNothing.setOnClickListener(view -> {
            sortType = Constant.SORT_BY_NOTHING;
            listener.sortByClick(sortType);
            dismiss();
        });
        btn_sortByPrice.setOnClickListener(view -> {
            sortType = Constant.SORT_BY_PRICE;
            listener.sortByClick(sortType);
            dismiss();
        });
        btn_sortByDate.setOnClickListener(view -> {
            sortType = Constant.SORT_BY_DATE;
            listener.sortByClick(sortType);
            dismiss();
        });


        return builder.create();

    }
    public void initView(){
        btn_sortByNothing=view.findViewById(R.id.btn_sortByNothing);
        btn_sortByPrice=view.findViewById(R.id.btn_sortByPrice);
        btn_sortByDate=view.findViewById(R.id.btn_sortByDate);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (SortByDialog.SortByClickListener) context;
    }

    public interface SortByClickListener{
        void sortByClick(String sortType);
    }
}

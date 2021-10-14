package com.kursat.mobirollerecommerce.util;

public class Categories{
    public static final String[] titles={
            "ELECTRONIC",
            "FASHION",
            "FOOD",
            "HOME APPLIANCES",
            "EDUCATION",
            "SPORT",
            "TOYS",
            "BEAUTY",
            "PET SHOP"
    };
    public static int getTitleIndex(String title){
        if(title == null || title =="") return -1;
        int index=0;
        while(index<titles.length){
            if(titles[index]==title){
                return index;
            }
            else index++;
        }
        return 0;
    }
}

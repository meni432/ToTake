package com.menisamet.totake;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 07/09/16.
 */
public class ListDataItem {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private int id;
    private String listName;
    private List<ItemData> itemDataList;
    private String googlePlaceId = "ChIJ1XXAkwRAHRURIj88VL6V2Sw";
    private Date fromDate = new Date();
    private Date toDate = new Date();



    public ListDataItem() {
        this.itemDataList = new ArrayList<>();
    }

    public ListDataItem(int id, String listName) {
        this();
        this.id = id;
        this.listName = listName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<ItemData> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(List<ItemData> itemDataList) {
        this.itemDataList = itemDataList;
    }

    public void addItemDataList(ItemData itemData){
        if (this.itemDataList != null){
            this.itemDataList.add(itemData);
        }
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }


    @Override
    public String toString() {
        return getListName();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public String getRepresentativeFromToDate(){
        if (toDate != null && fromDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(fromDate) + " - " + sdf.format(toDate);
        }
        return "";
    }


}

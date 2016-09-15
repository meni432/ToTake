package com.menisamet.totake;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import com.google.android.gms.location.places.Place;

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
    private Date startDate = new Date();
    private Date endDate = new Date();
    private Place place = null;


    public ListDataItem() {
        this.itemDataList = new ArrayList<>();
    }

    public ListDataItem(int id, String listName) {
        this();
        this.id = id;
        this.listName = listName;
    }

    public ListDataItem(Place selectedPlace, Date startDate, Date endDate) {
        this.place = selectedPlace;
        this.startDate = startDate;
        this.endDate = endDate;
        this.listName = (String)selectedPlace.getName();
        itemDataList = new ArrayList<>();
        addItemDataList(new ItemData("ilay", 1));
        addItemDataList(new ItemData("noa", 2));
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
        return getListName() + " item list "+itemDataList;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public String getRepresentativeFromToDate(){
        if (endDate != null && startDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(startDate) + " - " + sdf.format(endDate);
        }
        return "";
    }

}

package com.menisamet.totake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 07/09/16.
 */
public class ListDataItem {

    private int id;
    private String listName;
    private List<ItemData> itemDataList;
    private String googlePlaceId = "ChIJ1XXAkwRAHRURIj88VL6V2Sw";
    private String fromDate = "13/02/12";
    private String toDate = "15/03/15";


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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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


}

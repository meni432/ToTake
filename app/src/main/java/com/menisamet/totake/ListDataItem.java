package com.menisamet.totake;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 07/09/16.
 */
public class ListDataItem {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private String placeName;
    public String placeId;
    public List<ItemData> itemDataList;
    private String googlePlaceId;
    public Date startDate = new Date();
    public Date endDate = new Date();
//    private Place place = null;


    public ListDataItem() {
        this.itemDataList = new ArrayList<>();
    }

//    public ListDataItem(String selectedPlaceID, Date startDate, Date endDate, String listName) {
//        this.placeId = selectedPlaceID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.listName = listName;
//        itemDataList = new ArrayList<>();
//      /*  addItemDataList(new ItemData("ilay", 1));
//        addItemDataList(new ItemData("noa", 2));*/

    public ListDataItem(String representiveName, String googlePlaceId, Date startDate, Date endDate){
        this();
        this.placeName = representiveName;
        this.googlePlaceId = googlePlaceId;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return placeId;
    }


    public String getListName(){
        return placeName;
    }

    public String getRepresentativeFromToDate(){
        if (endDate != null && startDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(startDate) + " - " + sdf.format(endDate);
        }
        return "";
    }

}

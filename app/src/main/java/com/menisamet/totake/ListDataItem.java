package com.menisamet.totake;

import android.graphics.Bitmap;

import com.menisamet.totake.Suggestion.SuggestionSystemCall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 07/09/16.
 */
public class ListDataItem {

    public static int currentId = 0;
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private String placeName;
    //    public String placeId = "";
    private List<ItemData> itemDataList;
    private String googlePlaceId;
    public Date startDate = new Date();
    public Date endDate = new Date();
    private String imagePath;
    private String imageName;

    protected Bitmap getBitmap() {
        return bitmap;
    }

    protected void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

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

    public ListDataItem(String representiveName, String googlePlaceId, Date startDate, Date endDate, String imagePath, String imageName) {
        this();
        this.placeName = representiveName;
        this.googlePlaceId = googlePlaceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<ItemData> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(List<ItemData> itemDataList) {
        this.itemDataList = itemDataList;
    }

    public void addItemDataList(ItemData itemData) {
        if (this.itemDataList != null) {
            SuggestionSystemCall suggestionSystemCall = new SuggestionSystemCall(currentId);
            suggestionSystemCall.addLike(itemData.getName() + " " + itemData.getNumbers());
            this.itemDataList.add(itemData);
        }
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }


    public String getListName() {
        return placeName;
    }

    public String getRepresentativeFromToDate() {
        if (endDate != null && startDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(startDate) + " - " + sdf.format(endDate);
        }
        return "";
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}

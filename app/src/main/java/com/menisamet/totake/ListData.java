package com.menisamet.totake;

import java.util.List;

/**
 * Created by meni on 07/09/16.
 */
public class ListData {

    private int id;
    private String listName;
    private List<ItemData> itemDataList;

    public ListData() {
    }

    public ListData(int id, String listName) {
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

    @Override
    public String toString() {
        return getListName();
    }


}

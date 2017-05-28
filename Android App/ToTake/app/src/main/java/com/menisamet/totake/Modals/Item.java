package com.menisamet.totake.Modals;

import com.google.gson.annotations.SerializedName;
import com.menisamet.totake.GuiElement.CardsRecyclerView;
import com.menisamet.totake.Server.Interfaces.ItemInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 17/04/17.
 */

public class Item implements ItemInterface, CardsRecyclerView.CardElement{

    @SerializedName("itemName")
    private String mItemName;
    @SerializedName("itemAmount")
    private long mItemAmount;
    @SerializedName("done")
    private boolean mIsDone;
    private int itemID;

    public Item() {};

    public Item(String itemName, long itemAmount, int itemID) {
        this.mItemName = itemName;
        this.mItemAmount = itemAmount;
        this.itemID = itemID;
    }

    @Override
    public String getItemName() {
        return mItemName;
    }

    public int getItemID() {
        return itemID;
    }

    @Override
    public long getItemAmount() {
        return mItemAmount;
    }

    public void setmItemAmount(long mItemAmount) {
        this.mItemAmount = mItemAmount;
    }

    @Override
    public boolean isDone() {
        return mIsDone;
    }

    public void setIsDone(boolean IsDone) {
        this.mIsDone = IsDone;
    }

    /**
     * generate sample item
     *
     * @param numItems number of item to generate
     * @return Sample item array
     */
    public static List<Item> createItemList(int numItems) {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            items.add(new Item("Long Item "+ (i+1), (int)(Math.random()*20),i));
        }

        return items;
    }

    public static List<Item> createItemList(int numItems, String query) {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            items.add(new Item(query + " " + (i+1), (int)(Math.random()*20),i));
        }

        return items;
    }

    /**
     * CardsRecyclerView.CardElement Interface Area
     */

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public String getText() {
        return getItemName();
    }

    @Override
    public int getImageViewRecurse() {
        return 0;
    }

    /**
     * End CardsRecyclerView.CardElement Area
     */

    @Override
    public String toString() {
        return "Item{" +
                "mItemName='" + mItemName + '\'' +
                ", mItemAmount=" + mItemAmount +
                ", mIsDone=" + mIsDone +
                ", itemID=" + itemID +
                '}';
    }
}

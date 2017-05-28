package website.totake;

import website.totake.ClientInterfaces.ItemInterface;


/**
 * Created by meni on 26/05/17.
 */


public class Item  implements ItemInterface{
    private static int staticItemId = 1;
    private String mItemEHName;
    private String mItemHEName;
    private long mItemAmount;
    private boolean mIsDone;
    private int itemID;


    public Item() {
    }

    public Item(String itemName, long itemAmount) {
        this.mItemEHName = itemName;
        this.mItemAmount = itemAmount;
        this.itemID = staticItemId++;
    }

    @Override
    public String getItemName() {
        return mItemEHName;
    }

    @Override
    public long getItemAmount() {
        return mItemAmount;
    }

    @Override
    public boolean isDone() {
        return mIsDone;
    }

    @Override
    public long getItemId() {
        return itemID;
    }
}

package website.totake;

import website.totake.ClientInterfaces.TripInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 26/05/17.
 */
public class Trip implements TripInterface {
    private String mDestinationName;
    private Date mStartDate;
    private Date mEndDate;
    private List<Item> mListOfItems; //a list of item to take to this trip
    private int tripID;

    public Trip(String mDestinationName, Date mStartDate, Date mEndDate, int tripID) {
        this.mDestinationName = mDestinationName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.tripID = tripID;
        mListOfItems = new ArrayList<>();
    }

    public void addItem(Item item) {
        mListOfItems.add(item);
    }

    public int getTripID() {
        return tripID;
    }

    @Override
    public String getDestinationName() {
        return mDestinationName;
    }

    @Override
    public Date getStartDate() {
        return mStartDate;
    }

    @Override
    public Date getEndDate() {
        return mEndDate;
    }

    public List<Item> getListOfItems() {
        return mListOfItems;
    }
}

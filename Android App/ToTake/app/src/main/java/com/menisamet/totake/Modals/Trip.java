package com.menisamet.totake.Modals;

import com.google.gson.annotations.SerializedName;
import com.menisamet.totake.Server.Interfaces.TripInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 16/04/17.
 */

public class Trip implements TripInterface {
    @SerializedName("destinationEnName")
    private String mDestinationName;
//    @SerializedName("startDate")
    private Date mStartDate;
//    @SerializedName("endDate")
    private Date mEndDate;
    @SerializedName("sqlItemDetails")
    private List<Item> items;
    @SerializedName("tripId")
    private int tripID;

    public Trip() {}

    public Trip(String destinationName, Date startDate, Date endDate, int tripID) {
        this.mDestinationName = destinationName;
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.tripID = tripID;
        items =  new ArrayList<>();
        initialSampleItems(20);
    }

    private void initialSampleItems(int numberOfItems) {
        List<Item> items = Item.createItemList(numberOfItems);
        for (Item item : items) {
            this.items.add(item);
        }
    }

    public void setItemAmount(int itemId, long newAmount){
        items.get(itemId).setmItemAmount(newAmount);
    }
    @Override
    public String getDestinationName() {
        return mDestinationName;
    }

    public int getTripID() {
        return tripID;
    }

    @Override
    public Date getStartDate() {
        return mStartDate;
    }

    @Override
    public Date getEndDate() {
        return mEndDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItemToList(Item item){
        items.add(0, item);
    }

    public void removeItemFromList(int itemId){
        items.remove(itemId);
    }

    /**
     * generate sample trip
     *
     * @param numTrips number of trip to generate
     * @return Sample trip array
     */
    public static ArrayList<Trip> createTripList(int numTrips) {
        ArrayList<Trip> trips = new ArrayList<>();

        for (int i = 0; i < numTrips; i++) {
            trips.add(new Trip("Dest " + (i + 1), new Date(), new Date(),i));
        }

        return trips;
    }

    public String sFromToDates() {
        final String displayFormt = "%s to %s";
        final String dateFromat = "dd/MM/yyyy";

        if (this.getStartDate() == null || this.getEndDate() == null) {
            return "NO Dates";
        }

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat(dateFromat);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String startDate = df.format(this.getStartDate());
        String endDate = df.format(this.getEndDate());

        return String.format(displayFormt, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "mDestinationName='" + mDestinationName + '\'' +
                ", mStartDate=" + mStartDate +
                ", mEndDate=" + mEndDate +
                ", items=" + items +
                ", tripID=" + tripID +
                '}';
    }
}
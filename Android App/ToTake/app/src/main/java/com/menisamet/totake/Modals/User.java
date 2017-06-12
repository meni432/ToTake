package com.menisamet.totake.Modals;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public class User {
    @SerializedName("userName")
    private String userName;
    @SerializedName("userId")
    private int userId;
    @SerializedName("trips")
    private List<Trip> userTripList;

    public User() {
    }

    ;

    public User(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
        this.userTripList = new ArrayList<Trip>();
    }

    public User(String userName, int userId, ArrayList<Trip> userTrips) {
        this.userName = userName;
        this.userId = userId;
        this.userTripList = new ArrayList<Trip>(userTrips);
    }

    public User(User user) {
        this.userName = user.getNameUser();
        this.userId = user.getIdUser();
        this.userTripList = new ArrayList<Trip>(user.getTrips());
    }

    public String getNameUser() {
        return userName;
    }

    public int getIdUser() {
        return userId;
    }

    public List<Trip> getTrips() {
        return userTripList;
    }

    public void addNewTrip(Trip trip) {
        userTripList.add(trip);
    }

    /**
     * @param tripId which trip to return
     * @return the wanted trip
     */
    public Trip getTrip(long tripId) {
        for (Trip trip : userTripList) {
            if (trip.getTripID() == tripId) {
                return trip;
            }
        }
        return null;
    }

    public void deleteTrip(int tripId) {
        for (int i = 0; i < userTripList.size(); i++) {
            if (userTripList.get(i).getTripID() == tripId) {
                userTripList.remove(i);
                return;
            }
        }
    }

    public void addItemToTrip(int tripId, Item item) {
        for (int i = 0; i < userTripList.size(); i++) {
            if (userTripList.get(i).getTripID() == tripId) {
                userTripList.get(i).addItemToList(item);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", userTripList=" + userTripList +
                '}';
    }
}

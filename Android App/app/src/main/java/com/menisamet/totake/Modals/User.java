package com.menisamet.totake.Modals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public class User {
    private String userName;
    private int userId;
    private List<Trip> userTripList;

    public User() {};
    public User(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
//        this.userTripList = new ArrayList<Trip>();
        this.userTripList= new ArrayList<Trip>();
    }

    public User(String userName, int userId, ArrayList<Trip> userTrips){
        this.userName = userName;
        this.userId = userId;
        this.userTripList = new ArrayList<Trip>(userTrips);
    }

    public User(User user) {
        this.userName = user.getNameUser();
        this.userId = user.getIdUser();
        this.userTripList = new ArrayList<Trip>(user.getUserTripList());
    }

    public int getNumOfTrips(){
        return userTripList.size();
    }

    public String getNameUser() {
        return userName;
    }

    public int getIdUser() {
        return userId;
    }

    public List<Trip> getUserTripList() {
        return userTripList;
    }

    public void addNewTrip(Trip trip){
        userTripList.add(trip);
    }

    /**
     * @param tripId which trip to return
     * @return the wanted trip
     */
    public Trip getTrip(int tripId){
        for (int i = 0; i < getNumOfTrips(); i++) {
            if(userTripList.get(i).getTripID() == tripId) return userTripList.get(i);
        }
        return null;
    }

    public void deleteTrip(int tripId){
        for (int i = 0; i < getNumOfTrips(); i++) {
            if(userTripList.get(i).getTripID() == tripId) {
                userTripList.remove(i);
                return;
            }
        }
    }

    public void addItemToTrip(int tripId, Item item){
        for (int i = 0; i < getNumOfTrips(); i++) {
            if(userTripList.get(i).getTripID() == tripId) {
                userTripList.get(i).addItemToList(item);
                return;
            }
        }
    }

}

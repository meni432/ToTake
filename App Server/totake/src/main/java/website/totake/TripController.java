package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.Trip;
import website.totake.SqlStructure.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 26/05/17.
 */

@RestController
public class TripController {
    @Autowired
    private TripService tripService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDetailsService itemDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addNewTrip")
    public Trip addNewTrip(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                           @RequestParam(name = "destinationName", defaultValue = "none") String destinationName,
                           @RequestParam(name = "startDate", defaultValue = "-1") long startDate,
                           @RequestParam(name = "endDate", defaultValue = "-1") long endDate,
                           @RequestParam(name = "googlePlaceId", defaultValue = "none") String googlePlaceId) {

        User user = userService.getUser(userId);
        Trip trip = tripService.addNewTrip(destinationName, destinationName, googlePlaceId, new Date(startDate), new Date(endDate));
        user.addTrip(trip);
        userService.save(user);

        return trip;
    }


    @RequestMapping("/deleteTrip")
    public void deleteTrip(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                           @RequestParam(name = "tripId", defaultValue = "-1") long tripId) {

        User user = userService.getUser(userId);
        Trip trip = user.findTripById(tripId);
        trip.setStatus(Trip.TRIP_DELETED);
        userService.save(user);
    }

    @RequestMapping("/getTrip")
    public Trip getTrip(@RequestParam(value = "tripId", defaultValue = "-1") long tripId) {
        return tripService.getTrip(tripId);
    }

    @RequestMapping("/getTripList")
    public List<Long> getTripList(@RequestParam(value = "userId", defaultValue = "-1") long userId) {
        List<Long> tripIdsList = new ArrayList<>();
        List<Trip> userTrips = userService.getUser(userId).getTrips();
        for (Trip trip: userTrips) {
            tripIdsList.add(trip.getTripId());
        }
        return tripIdsList;
    }

}

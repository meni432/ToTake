package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.SqlTrip;

import java.util.ArrayList;
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



    @RequestMapping("/getTrip")
    public SqlTrip getTrip(@RequestParam(value = "tripId", defaultValue = "-1") long tripId) {
        return tripService.getTrip(tripId);
    }

    @RequestMapping("/getTripList")
    public List<Long> getTripList(@RequestParam(value = "userId", defaultValue = "-1") long userId) {
        List<Long> tripIdsList = new ArrayList<>();
        List<SqlTrip> userTrips = userService.getUser(userId).getTrips();
        for (SqlTrip trip: userTrips) {
            tripIdsList.add(trip.getTripId());
        }
        return tripIdsList;
    }

}

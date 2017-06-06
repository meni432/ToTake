package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Repositories.SqlItemRepository;
import website.totake.Repositories.SqlTripRepository;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

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

    @RequestMapping("/getTrip")
    public SqlTrip getTrip(@RequestParam(value = "tripId", defaultValue = "-1") long tripId) {
        SqlTrip sqlTrip = tripService.addNewTrip("Israel", "ישראל", new Date(111), new Date(999));
        SqlItem sqlItem = itemService.addNewItem("Test 2 item", "פריט בדיקה");
        itemDetailsService.addNewItemDetails(sqlTrip, sqlItem, 3, 1);
        sqlTrip = tripService.save(sqlTrip);
        return sqlTrip;

//        Trip trip = new Trip("DestTest", new Date(111), new Date(222), tripId);
//        trip.addItem(new Item("item 1", 1));
//        trip.addItem(new Item("item 2", 2));
//        return trip;
    }

    @RequestMapping("/getTripList")
    public List<Integer> getTripList(@RequestParam(value = "userId", defaultValue = "-1") int userId) {
        ArrayList<Integer> ansIds = new ArrayList<>();
        int size = (int)(Math.random()*10) + 3;
        for (int i = 0; i < 5; i++) {
            ansIds.add((int) (Math.random()*100));
        }
        return ansIds;
    }


}

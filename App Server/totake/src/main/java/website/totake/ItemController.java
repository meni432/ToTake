package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

/**
 * Created by meni on 26/05/17.
 */
@RestController
public class ItemController {
//    @Autowired
//    private SqlItemRepository sqlItemRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDetailsService itemDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/deleteItemFromTrip")
    public Item deleteItemFromTrip(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                   @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                   @RequestParam(name = "itemId", defaultValue = "-1") long itemId) {

        itemDetailsService.removeByTripIdAndItemId(tripId, itemId);
        return null;
    }

    @RequestMapping("/notifyChangeAmount")
    public SqlItemDetails notifyChangeAmount(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                   @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                   @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                   @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        SqlItemDetails sqlItemDetails = itemDetailsService.getItemDetails(tripId, itemId);
        sqlItemDetails.setAmount(amount);
        itemDetailsService.save(sqlItemDetails);

        return sqlItemDetails;
    }

    @RequestMapping("/addNewItem")
    public SqlItem addNewItem(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                              @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                              @RequestParam(name = "itemName", defaultValue = "none") String itemName,
                              @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        SqlTrip sqlTrip = tripService.getTrip(tripId);
        SqlItem sqlItem = itemService.addNewItem(itemName, itemName);
        SqlItemDetails sqlItemDetails = itemDetailsService.addNewItemDetails(sqlTrip, sqlItem, amount, 0);
        return sqlItemDetails.getItem();
    }


    @RequestMapping("/assignItemToUser")
    public SqlItem assignItemToUser(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                    @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                    @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                    @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        SqlTrip sqlTrip = tripService.getTrip(tripId);
        SqlItem sqlItem = itemService.getItem(itemId);
        SqlItemDetails sqlItemDetails = itemDetailsService.addNewItemDetails(sqlTrip, sqlItem, amount, 0);
        return sqlItemDetails.getItem();
    }

    @RequestMapping("/getAllItems")
    public Iterable<SqlItem> getAllItems() {
        return itemService.getAllItems();
    }
}

package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.Item;
import website.totake.SqlStructure.ItemDetails;
import website.totake.SqlStructure.Trip;

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

        itemDetailsService.removeByTripAndItem(tripId, itemId);
        return null;
    }

    @RequestMapping("/notifyChangeAmount")
    public ItemDetails notifyChangeAmount(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                          @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                          @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                          @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        ItemDetails itemDetails = itemDetailsService.getItemDetails(tripId, itemId);
        itemDetails.setAmount(amount);
        itemDetailsService.save(itemDetails);

        return itemDetails;
    }

    @RequestMapping("/addNewItem")
    public Item addNewItem(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                           @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                           @RequestParam(name = "itemName", defaultValue = "none") String itemName,
                           @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        Trip trip = tripService.getTrip(tripId);
        Item item = itemService.addNewItem(itemName, itemName);
        ItemDetails itemDetails = itemDetailsService.addNewItemDetails(trip, item, amount, 0);
        return itemDetails.getItem();
    }


    @RequestMapping("/assignItemToUser")
    public Item assignItemToUser(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                 @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                 @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                 @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        Trip trip = tripService.getTrip(tripId);
        Item item = itemService.getItem(itemId);
        ItemDetails itemDetails = itemDetailsService.addNewItemDetails(trip, item, amount, 0);
        return itemDetails.getItem();
    }

    @RequestMapping("/getAllItems")
    public Iterable<Item> getAllItems() {
        return itemService.getAllItems();
    }
}

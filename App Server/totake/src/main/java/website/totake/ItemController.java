package website.totake;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import io.prediction.EngineClient;
import io.prediction.Event;
import io.prediction.EventClient;
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
import website.totake.SqlStructure.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

        Trip trip = tripService.getTrip(tripId);
        ItemDetails itemDetails = trip.getItemSpecificDetails(itemId);
        itemDetails.setIsDone(1);
        itemDetailsService.save(itemDetails);
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

    @RequestMapping("/getSimilarItems")
    public String getSimilarItems(@RequestParam(name = "itemId", defaultValue = "i1") String itemId,
                                  @RequestParam(name = "amount", defaultValue = "4") long amount) {
        // create client object
        JsonObject response = null;
        EngineClient engineClient = new EngineClient("http://95.85.54.205:8000");

        // query

        try {
            response = engineClient.sendQuery(ImmutableMap.<String, Object>of(
                    "items", ImmutableList.of(itemId),
                    "num", amount
            ));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }


    @RequestMapping("/assignItemToUser")
    public Item assignItemToUser(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                 @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                 @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                 @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        Trip trip = tripService.getTrip(tripId);
        Item item = itemService.getItem(itemId);
        ItemDetails itemDetails = itemDetailsService.addNewItemDetails(trip, item, amount, 0);
        try {
            EventClient client = new EventClient(Defaults.PIO_SERVER, Defaults.PIO_ACCESS_KEY);
            Event viewEvent = new Event()
                    .event("view")
                    .entityType("user")
                    .entityId("u" + userId)
                    .targetEntityType("item")
                    .targetEntityId("i" + itemId);
            client.createEvent(viewEvent);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDetails.getItem();
    }

    @RequestMapping("/getAllItems")
    public Iterable<Item> getAllItems() {
        return itemService.getAllItems();
    }
}

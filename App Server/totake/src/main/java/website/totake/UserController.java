package website.totake;

import io.prediction.Event;
import io.prediction.EventClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by meni on 05/06/17.
 */
@RestController
public class UserController {
    private TripService tripService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDetailsService itemDetailsService;

    @Autowired
    private UserService userService;


    @RequestMapping("/getUser")
    public User getUser(@RequestParam(name = "userName", defaultValue = "none") String userName,
                        @RequestParam(name = "userId", defaultValue = "-1") long userId) {
        User user = null;
        if (!userName.equals("none")) {
            user = userService.findUserByUserName(userName);
        } else if (userId != -1) {
            user = userService.getUser(userId);
        }
        return user;
    }

    @RequestMapping("/getFireBaseUser")
    public User getFireBaseUser(@RequestParam(name = "userId", defaultValue = "none") String userId,
                                @RequestParam(name = "displayName", defaultValue = "none") String displayName) {
        User user = null;
        if (!userId.equals("none")) {
            user = userService.findUserByFirebaseId(userId);
            // Create a new user
            if (user == null) {
                user = userService.addNewUser(displayName, userId);
                JSONObject json = new JSONObject();
                try {
                    json.put("event", "$set");
                    json.put("entityType", "user");
                    json.put("entityId", Defaults.PIO_USER_PREFIX + user.getUserId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                EventPrediction.getInstance().sendEvent(json);
            }
        }
        return user;
    }


    private void createUser(String userName) {

    }

    @RequestMapping("/addUser")
    public User addUser(@RequestParam(name = "userName", defaultValue = "none") String userName,
                        @RequestParam(name = "userEmail", defaultValue = "none") String userEmail) {
        if (!userName.equals("none")) {
            User user = null;
//            try {
            user = userService.addNewUser(userName, userEmail);
//            } catch (Exception e) {
//                return null;
//            }
            return user;
        }
        return null;
    }

}

package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.User;

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
            if (user == null) {
                user = userService.addNewUser(displayName, userId);
            }
        }
        return user;
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

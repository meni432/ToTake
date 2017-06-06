package website.totake;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.SqlUser;

import javax.validation.ConstraintViolationException;

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
    public SqlUser getUser(@RequestParam(name = "userName", defaultValue = "none") String userName,
                           @RequestParam(name = "userId", defaultValue = "-1") long userId) {
        SqlUser sqlUser = null;
        if (!userName.equals("none")) {
            sqlUser = userService.findUserByUserName(userName);
        } else if (userId != -1) {
            sqlUser = userService.getUser(userId);
        }
        return sqlUser;
    }

    @RequestMapping("/addUser")
    public SqlUser addUser(@RequestParam(name = "userName", defaultValue = "none") String userName,
                           @RequestParam(name = "userEmail", defaultValue = "none") String userEmail) {
        if (!userName.equals("none")) {
            SqlUser user = null;
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

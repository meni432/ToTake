package website.totake;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.SqlUser;

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


    @RequestMapping("/addUser")
    public SqlUser addUser(@RequestParam(name = "userName", defaultValue = "none") String userName) {
        if (!userName.equals("none")) {
            return userService.addNewUser(userName);
        }
        return null;
    }

}

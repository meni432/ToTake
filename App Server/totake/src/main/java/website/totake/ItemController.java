package website.totake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Repositories.SqlItemRepository;
import website.totake.SqlStructure.SqlItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 26/05/17.
 */
@RestController
public class ItemController {
    @Autowired
    private SqlItemRepository sqlItemRepository;

    @RequestMapping("/getAllItems")
    public List<website.totake.Item> getAllItems() {
        ArrayList<website.totake.Item> allItems = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            allItems.add(new website.totake.Item("SqlItem "+i, 1));
        }
        return allItems;
    }

    @RequestMapping("/addItem")
    public SqlItem addItem(@RequestParam(name = "heName", defaultValue = "none") String heName,
                           @RequestParam(name = "enName", defaultValue = "none") String enName) {

        SqlItem sqlTableItem = new SqlItem(heName, enName);
        SqlItem result = sqlItemRepository.save(sqlTableItem);

        return result;
    }
}

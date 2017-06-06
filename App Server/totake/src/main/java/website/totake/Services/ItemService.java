package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemRepository;
import website.totake.Services.Interfaces.IItemService;
import website.totake.SqlStructure.SqlItem;

/**
 * Created by meni on 28/05/17.
 */
@Service
@Transactional
public class ItemService implements IItemService {
    @Autowired
    private SqlItemRepository sqlItemRepository;

    public SqlItem getItem(long itemId) {
        SqlItem item = sqlItemRepository.findOne(itemId);
        return item;
    }

    public Iterable<SqlItem> getAllItems() {
        return sqlItemRepository.findAll();
    }

    public SqlItem addNewItem(String mItemEHName, String mItemHEName) {
        SqlItem newItem = new SqlItem(mItemEHName, mItemHEName);
        SqlItem result = sqlItemRepository.save(newItem);
        return result;
    }

    @Override
    public SqlItem save(SqlItem sqlItem) {
        return sqlItemRepository.save(sqlItem);
    }
}

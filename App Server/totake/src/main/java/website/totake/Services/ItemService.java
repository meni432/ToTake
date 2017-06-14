package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemRepository;
import website.totake.Services.Interfaces.IItemService;
import website.totake.SqlStructure.Item;

/**
 * Created by meni on 28/05/17.
 */
@Service
@Transactional
public class ItemService implements IItemService {
    @Autowired
    private SqlItemRepository sqlItemRepository;

    public Item getItem(long itemId) {
        Item item = sqlItemRepository.findOne(itemId);
        return item;
    }

    public Iterable<Item> getAllItems() {
        return sqlItemRepository.findAll();
    }

    public Item addNewItem(String mItemEHName, String mItemHEName) {
        Item newItem = new Item(mItemEHName, mItemHEName);
        Item result = sqlItemRepository.save(newItem);
        return result;
    }

    @Override
    public Item save(Item item) {
        return sqlItemRepository.save(item);
    }
}

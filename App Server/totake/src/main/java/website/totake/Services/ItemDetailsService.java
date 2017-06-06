package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemDetailsRepository;
import website.totake.Services.Interfaces.IItemDetailsService;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

import java.util.List;

/**
 * Created by meni on 04/06/17.
 */
@Service
@Transactional
public class ItemDetailsService implements IItemDetailsService {
    @Autowired
    private SqlItemDetailsRepository sqlItemDetailsRepository;

    @Override
    public SqlItemDetails addNewItemDetails(SqlTrip sqlTrip, SqlItem sqlItem, int amount, int isDone) {
        SqlItemDetails sqlItemDetails = new SqlItemDetails();
        sqlItemDetails.setTrip(sqlTrip);
        sqlItemDetails.setItem(sqlItem);
        sqlItemDetails.setAmount(amount);
        sqlItemDetails.setIsDone(isDone);
        SqlItemDetails result = sqlItemDetailsRepository.save(sqlItemDetails);
        return result;
    }

    @Override
    public SqlItemDetails save(SqlItemDetails sqlItemDetails) {
        return sqlItemDetailsRepository.save(sqlItemDetails);
    }

    @Override
    public void removeByTripIdAndItemId(SqlTrip sqlTrip, SqlItem sqlItem) {
        sqlItemDetailsRepository.removeByTripIdAndItemId(sqlTrip.getTripId(), sqlItem.getItemID());
    }

    @Override
    public void removeByTripIdAndItemId(long tripId, long itemId) {
        sqlItemDetailsRepository.removeByTripIdAndItemId(tripId, itemId);
    }

    @Override
    public SqlItemDetails getItemDetails(long tripId, long itemId) {
        List<SqlItemDetails> sqlItemDetails = sqlItemDetailsRepository.findByTripIdAndItemId(tripId, itemId);
        if(sqlItemDetails.size() == 1) {
            return sqlItemDetails.get(0);
        }
        return null;
    }
}

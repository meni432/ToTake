package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemDetailsRepository;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

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
}

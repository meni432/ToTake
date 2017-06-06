package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.SqlTrip;
import website.totake.SqlStructure.SqlUser;

import java.util.List;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlUserRepository extends CrudRepository<SqlUser, Long> {
    List<SqlUser> findByUserName(String userName);
}

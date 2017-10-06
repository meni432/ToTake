package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.User;

import java.util.List;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlUserRepository extends CrudRepository<User, Long> {
    List<User> findByUserName(String userName);
    List<User> findByFirebaseId(String firebaseId);
}

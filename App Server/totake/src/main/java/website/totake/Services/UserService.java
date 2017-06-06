package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlUserRepository;
import website.totake.Services.Interfaces.IUserService;
import website.totake.SqlStructure.SqlUser;

import java.util.List;

/**
 * Created by meni on 05/06/17.
 */
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private SqlUserRepository sqlUserRepository;

    @Override
    public SqlUser getUser(long userId) {
        return sqlUserRepository.findOne(userId);
    }

    @Override
    public Iterable<SqlUser> getAllUsers() {
        return sqlUserRepository.findAll();
    }

    @Override
    public SqlUser addNewUser(String username, String userEmail) {
        SqlUser newUser = new SqlUser(username, userEmail);
        SqlUser result = sqlUserRepository.save(newUser);
        return result;
    }

    @Override
    public SqlUser save(SqlUser user) {
        return sqlUserRepository.save(user);
    }

    @Override
    public SqlUser findUserByUserName(String username) {
        List<SqlUser> sqlUsers = sqlUserRepository.findByUserName(username);
        if (sqlUsers.size() == 1) {
            return sqlUsers.get(0);
        }
        return null;
    }
}

package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlUserRepository;
import website.totake.Services.Interfaces.IUserService;
import website.totake.SqlStructure.User;

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
    public User getUser(long userId) {
        return sqlUserRepository.findOne(userId);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return sqlUserRepository.findAll();
    }

    @Override
    public User addNewUser(String username, String userEmail) {
        User newUser = new User(username, userEmail);
        User result = sqlUserRepository.save(newUser);
        return result;
    }

    @Override
    public User save(User user) {
        return sqlUserRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        List<User> users = sqlUserRepository.findByUserName(username);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
}

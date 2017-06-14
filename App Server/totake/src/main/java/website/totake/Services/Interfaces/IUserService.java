package website.totake.Services.Interfaces;

import website.totake.SqlStructure.User;

/**
 * Created by meni on 05/06/17.
 */
public interface IUserService {
    public User getUser(long userId);
    public Iterable<User> getAllUsers();
    public User addNewUser(String username, String userEmail);
    public User save(User user);
    public User findUserByUserName(String username);
}

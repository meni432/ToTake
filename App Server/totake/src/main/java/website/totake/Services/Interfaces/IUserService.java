package website.totake.Services.Interfaces;

import website.totake.SqlStructure.SqlUser;

/**
 * Created by meni on 05/06/17.
 */
public interface IUserService {
    public SqlUser getUser(long userId);
    public Iterable<SqlUser> getAllUsers();
    public SqlUser addNewUser(String username, String userEmail);
    public SqlUser save(SqlUser user);
    public SqlUser findUserByUserName(String username);
}

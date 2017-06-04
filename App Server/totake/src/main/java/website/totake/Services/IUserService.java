package website.totake.Services;

import website.totake.SqlStructure.SqlUser;

/**
 * Created by meni on 05/06/17.
 */
public interface IUserService {
    public SqlUser getUser(long userId);
    public Iterable<SqlUser> getAllUsers();
    public SqlUser addNewUser(String username);
    public SqlUser save(SqlUser user);
}

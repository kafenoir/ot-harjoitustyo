package hanzipractice.dao;

import java.util.List;
import hanzipractice.domain.User;

public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();
}

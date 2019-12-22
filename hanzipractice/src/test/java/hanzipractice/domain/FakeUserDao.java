
package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;
import hanzipractice.dao.UserDao;

public class FakeUserDao implements UserDao {
    List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User("tester", "Antti Nyymi"));
        users.add(new User("tester1", "Teppo Testaaja"));
        users.add(new User("tester2", "Benjamin Franklin"));
    }
    
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public User create(User user) {
        users.add(user);
        return user;
    } 
    
    @Override
    public List<User> getAll() {
        return users;
    }

}

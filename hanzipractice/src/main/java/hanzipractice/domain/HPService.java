
package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;
import hanzipractice.dao.UserDao;

/**
 * application logic
 */

public class HPService {
    
    private UserDao userDao;
    private User loggedIn;
    
    public HPService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    
    /**
     * creates a new user
     * 
     * @param username
     * @param name
     * 
     * @return true if creation successful, otherwise false
     */
    
    public boolean createUser(String username, String name) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try{
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * login
     * 
     * @param username
     * 
     * @return true, if username exists, otherwise false
     */
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    /**
     * log out
     */
    
    public void logout() {
        loggedIn = null;
    }
    
    /**
     * logged in user
     * 
     * @return logged in user
     */
    
    public User getLoggedUser() {
        return loggedIn;
    }
    
}

package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;
import hanzipractice.dao.UserDao;
import hanzipractice.dao.WordDao;
import hanzipractice.dao.MyListDao;
import java.util.HashMap;

/**
 * application logic
 */
public class HPService {

    private UserDao userDao;
    private User loggedIn;
    private WordDao wordDao;
    private Dictionary dictionary;
    private MyListDao myListDao;

    public HPService(UserDao userDao, WordDao wordDao, MyListDao myListDao) {
        this.userDao = userDao;
        this.wordDao = wordDao;
        this.myListDao = myListDao;
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
        try {
            userDao.create(user);
        } catch (Exception e) {
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

    public void readDictionary() {
        this.dictionary = new Dictionary(wordDao.getAll());
    }

    public void printDictionary() {
        System.out.println(dictionary);
    }

    public void createMyLists() {

        HashMap<String, int[]> m = myListDao.getAll();
        for (HashMap.Entry<String, int[]> pair : m.entrySet()) {
            ArrayList<Word> words = new ArrayList<>();
            MyList mlist = new MyList(userDao.findByUsername(pair.getKey()));

            for (int i = 0; i < pair.getValue().length; i++) {
                mlist.addWord(pair.getValue()[i], dictionary);
            }

        }
    }

}

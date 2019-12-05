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
    private MyList myList;
    private HashMap<String, ArrayList<Integer>> myLists;

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

    public void printMyList() {

        System.out.println(myList);
    }

    public void readMyLists() {

        ArrayList<Word> wl = new ArrayList<>();

        try {
            myLists = myListDao.getAll();

            for (Integer i : myLists.get(loggedIn.getUsername())) {
                Word w = dictionary.searchByWordID(i);
                wl.add(w);

            }
        } catch (Exception ex) {

        }
        myList = new MyList(loggedIn, wl);
    }

//    public void writeMyLists() {
//        
//        HashMap
//    }
    public void addWordToMyList(int id) {
        myList.addWord(id, dictionary);
        editMyLists();
    }

    public String removeWordFromMyList(int id) {
        if (myList.removeWord(id)) {
            editMyLists();
            return "Word removed!";
        }
        return "Invalid command!";
    }

    public void editMyLists() {
        myLists.put(loggedIn.getUsername(), myList.getWordIds());
        try {
            myListDao.edit(myLists);

        } catch (Exception ex) {

        }
    }
}

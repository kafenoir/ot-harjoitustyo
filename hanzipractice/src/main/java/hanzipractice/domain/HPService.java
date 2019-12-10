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

    /**
     * creates a dictionary from the dictionary file
     */
    public void readDictionary() {
        this.dictionary = new Dictionary(wordDao.getAll());
    }

    /**
     * prints the dictionary
     */
    public void printDictionary() {
        System.out.println(dictionary);
    }

    /**
     * prints the user's personal word list
     */
    public void printMyList() {

        System.out.println(myList);
    }

    /**
     * creates a personal word list for the logged user by matching IDs of the
     * words in the dictionary with those saved as a sequence in MyList file
     *
     */
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

    /**
     * adds a word to the user's personal word list by matching the given ID
     * with word ID's in the dictionary
     *
     * @param id user inputted word ID
     */
    public void addWordToMyList(int id) {
        myList.addWord(id, dictionary);
        editMyLists();
    }

    /**
     * removes a word with matching ID from the user's personal word list or
     * returns and error message if a matching word is not on the list
     *
     * @param id user inputted word ID
     *
     * @return String "Word removed! if word is on the list, String "Invalid
     * Command!" if word is not on the list
     */
    public String removeWordFromMyList(int id) {
        if (myList.removeWord(id)) {
            editMyLists();
            return "Word removed!";
        }
        return "Invalid command!";
    }

    /**
     * saves changes to the personal list into the file
     */
    public void editMyLists() {
        myLists.put(loggedIn.getUsername(), myList.getWordIds());
        try {
            myListDao.edit(myLists);

        } catch (Exception ex) {

        }
    }
}

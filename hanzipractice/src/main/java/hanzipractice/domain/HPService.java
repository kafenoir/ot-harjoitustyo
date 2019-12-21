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
    private Practice practice;

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

    public ArrayList<String> dictionaryAsStrings() {
        return dictionary.getDictionaryAsStrings();
    }

    /**
     * prints the user's personal word list
     */
    public String printMyList() {

        return myList.toString();
    }

    public ArrayList<String> myListAsStrings() {
        return myList.getWordsAsStrings();
    }

    /**
     * creates a personal word list for the logged user by matching IDs of the
     * words in the dictionary with those saved as a sequence in MyList file
     *
     */
    public void readMyLists() {

        try {
            myLists = myListDao.getAll();

        } catch (Exception ex) {

        }

    }

    public void createMyList() {

        ArrayList<Word> wl = new ArrayList<>();
        for (Integer i : myLists.get(loggedIn.getUsername())) {
            Word w = dictionary.searchByWordID(i);
            wl.add(w);

        }
        myList = new MyList(loggedIn, wl);
    }

    /**
     * adds a word to the user's personal word list by matching the given ID
     * with word ID's in the dictionary
     *
     * @param id user inputted word ID
     */
    public void addWordToMyList(String wordAsString) {
        String[] wordTable = wordAsString.split(" ");
        myList.addWord(Integer.valueOf(wordTable[0]), dictionary);
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
    public void removeWordFromMyList(String wordAsString) {

        String[] wordTable = wordAsString.split(" ");
        if (myList.removeWord(Integer.valueOf(wordTable[0]))) {
            editMyLists();

        }

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

    public void createPractice() {

        this.practice = new Practice(myList);

    }

    public String askQuestion() {

        Word q = practice.askWord();
        if (q == null) {
            return "GAME OVER";
        }
        
        String question = q.getHanzi();
        return question;

    }

    public Boolean isCorrect(String answer, int type) {
        if (type == 1) {
            if (practice.isCorrectPinyin(answer)) {
                return true;
            }
        }
        if (type == 2) {
            if (practice.isCorrectEng(answer)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isOver() {
        if (practice.getSize() == 0) {
            return true;
        }
        return false;
    }

    public String gameOver() {
        myList.setNewHighScore(practice.getScore());
        return practice.getScoreAsString();
    }
}

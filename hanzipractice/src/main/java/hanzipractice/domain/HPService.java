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
     * @return true if username exists, otherwise false
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
    public Dictionary readDictionary() {
        dictionary = new Dictionary(wordDao.getAll());
        return dictionary;
    }

    /**
     * returns the contents of the dictionary as strings
     *
     * these are used for creating the ListView in GUI
     *
     * @return
     */
    public ArrayList<String> dictionaryAsStrings() {
        return dictionary.getDictionaryAsStrings();
    }

    /**
     * returns the contents of the current user's word list as strings
     *
     * these are used for creating the ListView in GUI
     *
     * @return
     */
    public ArrayList<String> myListAsStrings() {
        return myList.getWordsAsStrings();
    }

    /**
     * Extracts all saved word lists from the mylist file
     *
     */
    public HashMap<String, ArrayList<Integer>> readMyLists() {

        try {
            myLists = myListDao.getAll();

        } catch (Exception ex) {

        }
        return myLists;
    }

    /**
     * creates a Word object list for the current user based on the username and
     * word IDs extracted from the mylist file
     */
    public MyList createMyList() {

        ArrayList<Word> wl = new ArrayList<>();

        if (myLists.containsKey(loggedIn.getUsername())) {
            for (Integer i : myLists.get(loggedIn.getUsername())) {
                Word w = dictionary.searchByWordID(i);
                wl.add(w);

            }
        }
        myList = new MyList(loggedIn, wl);
        return myList;
    }

    /**
     * Extracts the word id from dictionary ListView string and adds the
     * corresponding word to myList
     *
     * @param wordAsString String representation of a word from dictionary
     * ListView
     */
    public void addWordToMyList(String wordAsString) {
        String[] wordTable = wordAsString.split(" ");
        myList.addWord(Integer.valueOf(wordTable[0]), dictionary);
        editMyLists();
    }

    /**
     * Extracts the word id from my list ListView string and removes the
     * corresponding word from myList
     *
     * @param wordAsString String representation of a word from my list ListView
     */
    public void removeWordFromMyList(String wordAsString) {

        String[] wordTable = wordAsString.split(" ");
        myList.removeWord(Integer.valueOf(wordTable[0]));
        editMyLists();
    }

    /**
     * saves changes to the word list into mylist file
     */
    public void editMyLists() {
        myLists.put(loggedIn.getUsername(), myList.getWordIds());
        try {
            myListDao.edit(myLists);

        } catch (Exception ex) {

        }
    }

    /**
     * creates a new practice
     */
    public void createPractice() {

        this.practice = new Practice(myList);
    }

    /**
     * generates the next question
     *
     * @return returns the next question if questions left, otherwise returns
     * "GAME OVER"
     */
    public String askQuestion() {

        Word q = practice.askWord();
        if (q == null) {
            return "GAME OVER";
        }

        String question = q.getHanzi();
        return question;
    }

    /**
     * checks if the user's answer to the question is correct
     *
     * @param answer user inputted answer
     * @param type game mode
     * @return true if the answer is correct, otherwise false
     */
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

    /**
     * checks if the current game is over
     *
     * @return true if the game is over, otherwise false
     */
    public Boolean isOver() {
        if (practice.getSize() == 0) {
            return true;
        }
        return false;
    }

    /**
     * end of game operations: shows user's practice score, sets a new high
     * score for the current list, if achieved
     *
     * @return user's score for the completed practice
     */
    public String gameOver() {

        return practice.getScoreAsString();
    }
}

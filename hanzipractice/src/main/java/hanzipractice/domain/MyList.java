package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents user's personal word list
 */
public class MyList {

    private List<Word> words;
    private User user;
    private int score;

    public MyList(User user, List<Word> words) {
        this.user = user;
        this.words = words;
        this.score = 0;

    }

    public User getUser() {
        return user;
    }

    public List<Word> getWords() {
        return words;
    }

    /**
     * get a presentation of the user's word list as list of word ids
     *
     * @return user's word list as a list of word ids
     */
    public ArrayList<Integer> getWordIds() {

        ArrayList<Integer> wl = new ArrayList();

        if (!words.isEmpty()) {
            for (Word word : words) {
                wl.add(word.getID());
            }
        }

        return wl;

    }

    /**
     * creates an ArrayList of string representations of the Word objects in
     * words
     *
     * @return words as a list of strings
     */
    public ArrayList<String> getWordsAsStrings() {
        ArrayList<String> wordStrings = new ArrayList<>();
        for (Word word : words) {
            wordStrings.add(word.toString());
        }

        return wordStrings;
    }

    /**
     * set a new high score if the final score exceeds the previous high score
     *
     * @param score final score for the practice
     */
    public void setNewHighScore(int score) {

        if (score > this.score) {
            this.score = score;
        }

    }

    /**
     * adds a word to the user's list by matching a given id to those in the
     * dictionary, resets high score
     *
     * @param id word id
     * @param dictionary the dictionary to be matched with
     */
    public void addWord(int id, Dictionary dictionary) {

        words.add(dictionary.searchByWordID(id));
        score = 0;

    }

    /**
     * removes a word from the user's list if it exists, resets high score
     *
     * @param id word id
     */
    public void removeWord(int id) {
        for (Word word : words) {
            if (word.getID() == id) {
                words.remove(word);
                score = 0;
                break;
            }
        }
    }
}

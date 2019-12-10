package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents user's personal word list
 */
public class MyList {

    private List<Word> words;
    private User user;

    public MyList(User user, List<Word> words) {
        this.user = user;
        this.words = words;

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
     * @return user's personal word list as a list of word ids
     */
    public ArrayList<Integer> getWordIds() {

        ArrayList<Integer> wl = new ArrayList();
        for (Word word : words) {
            wl.add(word.getID());
        }

        return wl;

    }

    /**
     * adds a word to the persona list by matching a given id to those in the
     * dictionary
     *
     * @param id word id
     * @param dictionary the dictionary to be matched with
     */
    public void addWord(int id, Dictionary dictionary) {

        words.add(dictionary.searchByWordID(id));

    }

    /**
     * removes a word from the personal list if it exists
     *
     * @param id word id
     * @return true if the word is successfully removed, false if the word is
     * not found
     */
    public boolean removeWord(int id) {
        for (Word word : words) {
            if (word.getID() == id) {
                words.remove(word);
                return true;
            }
        }
        return false;
    }

    /**
     * builds and returns a printable string of the personal word list
     *
     * @return contents of the personal word list as a string
     */

    @Override
    public String toString() {
        StringBuilder lB = new StringBuilder();
        for (Word word : words) {
            lB.append(word.toString() + "\n");
        }
        String l = lB.toString();
        return l;

    }
}

package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Integer> getWordIds() {

        ArrayList<Integer> wl = new ArrayList();
        for (Word word : words) {
            wl.add(word.getID());
        }

        return wl;

    }

    public void addWord(int id, Dictionary dictionary) {

        words.add(dictionary.searchByWordID(id));

    }

    public boolean removeWord(int id) {
        for (Word word : words) {
            if (word.getID() == id) {
                words.remove(word);
                return true;
            }
        }
        return false;
    }

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

package hanzipractice.domain;

import java.util.List;
import java.util.ArrayList;

public class MyList {

    private List<Word> myList;
    private User user;

    public MyList(User user) {
        this.user = user;

    }

    public void setWords() {

    }

    public User getUser() {
        return user;
    }

    public String getWordsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myList.size() - 1; i++) {
            Word word = myList.get(i);
            sb.append(word);
            sb.append(";");
        }

        sb.append(myList.get(myList.size() - 1).getID());
        return sb.toString();

    }

    public void addWord(int id, Dictionary dictionary) {

        myList.add(dictionary.searchByWordID(id));

    }

    public void removeWord(int id) {
        for (Word word : myList) {
            if (word.getID() == id) {
                myList.remove(word);
            }
        }
    }
}

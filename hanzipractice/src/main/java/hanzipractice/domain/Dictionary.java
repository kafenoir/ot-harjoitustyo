package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents the dictionary
 *
 */
public class Dictionary {

    private List<Word> words;

    public Dictionary(List words) {

        this.words = new ArrayList<Word>();
        this.words.addAll(words);
    }

    /**
     * creates an ArrayList of string representations of the Word objects in
     * words
     *
     * @return words as a list of strings
     */
    public ArrayList<String> getDictionaryAsStrings() {
        ArrayList<String> wordStrings = new ArrayList<String>();
        for (Word word : words) {
            wordStrings.add(word.toString());
        }
        return wordStrings;
    }

    /**
     * finds a Word from the Dictionary based on Word id
     *
     * @param id Word id
     * @return Word if found in the Dictionary, null if not found
     */
    public Word searchByWordID(int id) {
        return words.stream()
                .filter(w -> w.getID() == id)
                .findFirst()
                .orElse(null);
    }
}

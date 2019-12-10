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

        this.words = words;
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

    /**
     * builds and returns a printable string of the dictionary
     *
     * @return contents of the dictionary in a string
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

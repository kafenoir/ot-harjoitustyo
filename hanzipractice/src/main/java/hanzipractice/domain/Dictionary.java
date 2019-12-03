package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private List<Word> words;

    public Dictionary(List words) {

        this.words = words;
    }
    
    public Word searchByWordID(int id) {
        return words.stream()
                .filter(w -> w.getID() == id)
                .findFirst()
                .orElse(null);
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

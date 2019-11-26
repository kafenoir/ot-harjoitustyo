package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private List<Word> dictionary;

    public Dictionary(List words) {

        this.dictionary = words;
    }
    
    public Word searchByWordID(int id) {
        return dictionary.stream()
                .filter(w -> w.getID() == id)
                .findFirst()
                .orElse(null);
    }
    

    @Override
    public String toString() {
        StringBuilder lB = new StringBuilder();
        for (Word word : dictionary) {
            lB.append(word.getID() + "\t" + word.getHanzi()
                    + "\t" + word.getPinyin() + "\t\t" + word.getEngTrans() + "\n");
        }
        String l = lB.toString();
        return l;

    }

}

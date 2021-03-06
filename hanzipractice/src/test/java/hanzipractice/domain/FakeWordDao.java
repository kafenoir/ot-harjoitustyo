
package hanzipractice.domain;

import org.junit.Test;
import hanzipractice.dao.WordDao;
import java.util.ArrayList;
import java.util.List;


public class FakeWordDao implements WordDao{
    List<Word> words = new ArrayList<>();
    
    public FakeWordDao() {
        
        words.add(new Word(1,"yi","yi1","one"));
        words.add(new Word(2,"er","er2","two"));
        words.add(new Word(3,"san","san3","three"));
        words.add(new Word(4,"si","si4","four"));
        words.add(new Word(5,"wu","wu3","five"));
        words.add(new Word(6,"liu","liu4","six"));
    }

    @Override
    public Word findByID(int id) {
        return words.stream().filter(w->w.getID() == id).findFirst().orElse(null);
    }

    @Override
    public List<Word> getAll() {
        return words;
    }
    
}


package hanzipractice.domain;

import org.junit.Test;
import hanzipractice.dao.WordDao;
import java.util.ArrayList;
import java.util.List;


public class FakeWordDao implements WordDao{
    List<Word> words = new ArrayList<>();
    
    public FakeWordDao() {
        words.add(new Word(1, "yi", "yi1", "yksi"));
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

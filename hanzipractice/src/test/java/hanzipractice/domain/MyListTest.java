
package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class MyListTest {
    
    List<Word> words;
    User user;
    
    @Before
    public void setUp() {
        user = new User("tester", "Teppo Testaaja");
        Word w = new Word(1, "yi", "yi1", "one");
        words = new ArrayList<>();
        words.add(w);
        MyList ml = new MyList(user, words);
        
    }
    
    @Test
    public void correctToStringFormat(){
        
        String s = String.format("[%2s %2s %15s %15s]", 1, "yi", "yi1", "one");
        assertEquals(words.toString(), s);
    }
}

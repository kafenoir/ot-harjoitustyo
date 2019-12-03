
package hanzipractice.domain;

import org.junit.Test;
import static org.junit.Assert.*;


public class WordTest {
    
    @Test
    public void correctToStringFormat(){
        Word w = new Word(1, "yi", "yi1", "one");
        String s = String.format("%2s %2s %15s %15s", 1, "yi", "yi1", "one");
        assertEquals(w.toString(), s);
    }
    
     @Test
    public void equalWhenSameId() {
        Word w1 = new Word(1, null, null, null);
        Word w2 = new Word(1, null, null, null);
        assertTrue(w1.equals(w2));
    }
  
    @Test
    public void notEqualWhenDifferentId() {
        Word w1 = new Word(1, null, null, null);
        Word w2 = new Word(2, null, null, null);
        assertFalse(w1.equals(w2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Word t = new Word(1, null, null, null);
        Object o = new Object();
        assertFalse(t.equals(o));
    }      
    
}

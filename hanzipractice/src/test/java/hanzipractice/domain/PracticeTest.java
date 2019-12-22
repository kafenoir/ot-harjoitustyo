
package hanzipractice.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PracticeTest {
    
    Practice practice;
    MyList myList;
    List<Word> words;
    User u;
    
    @Before
    public void setUp() {
        u = new User("tester","Teppo");
        words = new ArrayList<>();
        
    }
    @Test
    public void askWordNullIfNoQuestions() {
        createPractice();
        assertEquals(practice.askWord(), null);
    }
    @Test
    public void trueIfCorrectPinyin() {
        words.add(new Word(1, "yi", "yi1", "one"));
        createPractice();
        practice.askWord();
        assertTrue(practice.isCorrectPinyin("yi1"));
    }
    @Test
    public void trueIfCorrectEnglish() {
        words.add(new Word(1, "yi", "yi1", "one"));
        createPractice();
        practice.askWord();
        assertTrue(practice.isCorrectEng("one"));
    }
    
    public void createPractice() {
        myList = new MyList(u, words);
        practice = new Practice(myList);
    }
    

}

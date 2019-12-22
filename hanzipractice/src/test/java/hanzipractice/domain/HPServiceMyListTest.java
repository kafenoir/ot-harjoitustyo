package hanzipractice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class HPServiceMyListTest {

    FakeWordDao wordDao;
    FakeUserDao userDao;
    FakeMyListDao myListDao;
    HPService service;
    MyList myList;
    HashMap<String, ArrayList<Integer>> myLists;

    @Before
    public void setUp() {
        wordDao = new FakeWordDao();
        userDao = new FakeUserDao();
        myListDao = new FakeMyListDao();
        service = new HPService(userDao, wordDao, myListDao);
        myLists = service.readMyLists();
    }

    @Test
    public void myListsContainsCorrectKeys() {

        assertTrue(myLists.containsKey("tester1"));
        assertTrue(myLists.containsKey("tester2"));
    }

    @Test
    public void myListsContainsCorrectValues() {

        ArrayList<Integer> mockList1 = myLists.get("tester1");
        ArrayList<Integer> mockList2 = myLists.get("tester2");
        ArrayList<Integer> testList1 = new ArrayList();
        ArrayList<Integer> testList2 = new ArrayList();

        for (int i = 1; i <= 5; i++) {
            testList1.add(i);
            testList2.add(i + 1);
        }

        assertEquals(mockList1, testList1);
        assertEquals(mockList2, testList2);

    }

    @Test
    public void dictionaryReadCorrectly() {
        ArrayList<Word> dictionaryWords = new ArrayList<>();
        dictionaryWords.add(new Word(1, "yi", "yi1", "one"));
        dictionaryWords.add(new Word(2, "er", "er2", "two"));
        dictionaryWords.add(new Word(3, "san", "san3", "three"));
        dictionaryWords.add(new Word(4, "si", "si4", "four"));
        dictionaryWords.add(new Word(5, "wu", "wu3", "five"));
        dictionaryWords.add(new Word(6, "liu", "liu4", "six"));
        Dictionary testDictionary = new Dictionary(dictionaryWords);
        Dictionary dictionary = service.readDictionary();
        assertEquals(dictionary.getDictionaryAsStrings(), testDictionary.getDictionaryAsStrings());

    }

    @Test
    public void newUserMyListIsEmpty() {
        Dictionary dictionary = service.readDictionary();
        service.login("tester");
        myList = service.createMyList();
        assertTrue(myList.getWords().isEmpty());
    }

    @Test
    public void oldUserMyListCreatedCorrectly() {
        createListForTesterOne();
        assertEquals(myList.getUser().getUsername(), "tester1");

        ArrayList<Integer> testIDs = new ArrayList<Integer>();
        testIDs.add(1);
        testIDs.add(2);
        testIDs.add(3);
        testIDs.add(4);
        testIDs.add(5);
        assertEquals(testIDs, myList.getWordIds());
    }

    @Test
    public void correctWordRemoved() {
        createListForTesterOne();
        String testWord = String.format("%s %2s %15s %15s", "1", "yi", "yi1", "one");
        service.removeWordFromMyList(testWord);
        assertFalse(myList.getWordIds().contains(1));
        assertTrue(myList.getWordIds().contains(2));

    }

    @Test
    public void correctWordAdded() {
        createListForTesterOne();
        String testWord = String.format("%s %2s %15s %15s", "6", "liu", "liu4", "six");
        service.addWordToMyList(testWord);
        assertTrue(myList.getWordIds().contains(6));
    }

    public void createListForTesterOne() {
        Dictionary dictionary = service.readDictionary();
        service.login("tester1");
        myList = service.createMyList();
    }

}

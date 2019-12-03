package hanzipractice.domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class HPServiceMyListTest {

    FakeWordDao wordDao;
    FakeUserDao userDao;
    FakeMyListDao myListDao;
    FakeEmptyMyListDao eDao;
    HPService service;
    Dictionary dictionary;
    MyList myList;

    @Before
    public void setUp() {
        wordDao = new FakeWordDao();
        userDao = new FakeUserDao();
        myListDao = new FakeMyListDao();
        service = new HPService(userDao, wordDao, myListDao);
        User u = new User("tester", "Teppo Testaaja");
        userDao.create(u);
        service.login("tester");
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(1,"yi","yi1","one"));
        dictionary = new Dictionary(words);

    }

//    @Test
//    public void noMyListFoundCreatesNew() {
//        eDao = new FakeEmptyMyListDao();
//        service = new HPService(userDao, wordDao, eDao);
//        service.readMyLists();
//        assertEquals(myList.getUser(), "tester");
//        assertEquals(myList.getWordIds().isEmpty(), true);
//    }
//
//    @Test
//    public void foundMyListIsReadCorrectly() {
//        service.readMyLists();
//        assertEquals(myList.getUser(), "testertester");
//        assertEquals(Integer.valueOf(myList.getWordIds().get(0)),
//                Integer.valueOf(1));
//    }

    @Test
    public void dictionaryReadCorrectly() {

    }

    @Test
    public void removeWordIfInTheList() {

    }

}

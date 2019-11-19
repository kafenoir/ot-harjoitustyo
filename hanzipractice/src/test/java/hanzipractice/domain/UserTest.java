package hanzipractice.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("tester", "Antti");
        User u2 = new User("tester", "Antti");
        assertTrue(u1.equals(u2));

    }

    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("teaser", "Antti");
        User u2 = new User("taster", "Kalle");
        assertFalse(u1.equals(u2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("tester", "Antti");
        Object o = new Object();
        assertFalse(u.equals(o));
    }

}

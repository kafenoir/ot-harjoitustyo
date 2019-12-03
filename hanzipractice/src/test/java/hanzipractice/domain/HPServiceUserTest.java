
package hanzipractice.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HPServiceUserTest {
    
    FakeWordDao wordDao;
    FakeUserDao userDao;
    FakeMyListDao myListDao;
    HPService service;
    
    @Before
    public void setUp() {
        wordDao = new FakeWordDao();
        userDao = new FakeUserDao();
        service = new HPService(userDao, wordDao, myListDao);     
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("tester");
        assertTrue(result);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Antti Nyymi", loggedIn.getName() );
    }
    
    @Test
    public void loggedInUserCanLogout() {
        service.login("tester");
        service.logout();
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = service.createUser("tester", "Antti Nyymi");
        assertFalse(result);
    }
    
    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = service.createUser("patu", "Patrik Turpeinen");
        assertTrue(result);
        
        boolean loginOk = service.login("patu");
        assertTrue(loginOk);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Patrik Turpeinen", loggedIn.getName() );
    } 
}

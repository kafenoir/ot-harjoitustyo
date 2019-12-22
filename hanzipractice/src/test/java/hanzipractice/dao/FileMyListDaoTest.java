
package hanzipractice.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


public class FileMyListDaoTest {
    
@Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File myListFile;  
    MyListFileDao dao;
    
    @Before
    public void setUp() throws Exception {
        myListFile = testFolder.newFile("testfile_mylist.txt");  
        
        try (FileWriter file = new FileWriter(myListFile.getAbsolutePath())) {
            file.write("tester;1;2;3;4;5\n");
        }
        
        dao = new MyListFileDao(myListFile.getAbsolutePath());
    }
   
    @Test
    public void myListReadCorrectlyFromFile() {
        HashMap<String, ArrayList<Integer>> map = dao.getAll();
        assertEquals(1, map.size());
        ArrayList<Integer> wordIDs = map.get("tester");
        int a = 3;
        int b = wordIDs.get(2);
        assertEquals(a, b);
    }
    
    
    @After
    public void tearDown() {
        myListFile.delete();
    }
}
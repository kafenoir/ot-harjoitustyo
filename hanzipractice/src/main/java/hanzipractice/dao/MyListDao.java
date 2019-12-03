package hanzipractice.dao;

import hanzipractice.domain.MyList;
import java.util.ArrayList;
import java.util.HashMap;

public interface MyListDao {

    HashMap<String, ArrayList<Integer>> getAll();
    

    boolean edit(HashMap<String, ArrayList<Integer>> myLists) throws Exception;

}

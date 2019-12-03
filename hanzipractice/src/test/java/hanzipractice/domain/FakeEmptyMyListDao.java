
package hanzipractice.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import hanzipractice.dao.MyListDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeEmptyMyListDao implements MyListDao {
    HashMap<String, ArrayList<Integer>> mLists; 
    
    
    public FakeEmptyMyListDao() {
        mLists = new HashMap<>();  
    }

    @Override
    public HashMap<String, ArrayList<Integer>> getAll() {
        return mLists;
    }

    @Override
    public boolean edit(HashMap<String, ArrayList<Integer>> myLists){
        return true;
    }
    
}

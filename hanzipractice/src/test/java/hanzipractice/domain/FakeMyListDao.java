
package hanzipractice.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import hanzipractice.dao.MyListDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeMyListDao implements MyListDao {
    HashMap<String, ArrayList<Integer>> mLists; 
    
    
    public FakeMyListDao() {
        mLists = new HashMap<>();
        ArrayList<Integer> mockList1 = new ArrayList();
        ArrayList<Integer> mockList2 = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            mockList1.add(i);
            mockList2.add(i+1);
        }
        mLists.put("tester1", mockList1);
        mLists.put("tester2", mockList2);
        
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


package hanzipractice.domain;

import java.util.List;

public class Practice {
    
    private MyList mList;
    private int points;
    private int maxPoints;
    
    public Practice(MyList mList) {
       this.mList = mList;
        
    }
    
    public void startPractice() {
        
    }
    
    public String askWord() {
        return "";
    }
    
    public Boolean checkIfCorrect(String answer) {
        return false;
    }
    
}

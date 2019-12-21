package hanzipractice.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Practice {

    private List<Word> words;
    private Word active;
    private Boolean[] answered;
    private int points;
    private int maxPoints;

    public Practice(MyList mList) {
        words = new ArrayList<>();
        words.addAll(mList.getWords());
        maxPoints = words.size();
        points = 0;
        answered = new Boolean[words.size()];
        Arrays.fill(answered, Boolean.FALSE);
    }

    public int getSize() {
        return words.size();
    }
    
    public int getScore() {
        return this.points;
    }

    public Word askWord() {
        
        if (words.isEmpty()) {
            return null;
        }
        
        Random r = new Random();

        Word a = words.get(r.nextInt(words.size()));
        active = a;
        words.remove(a);

        return a;

    }

    public Boolean isCorrectPinyin(String answer) {

        if (answer.equals(active.getPinyin())) {
            points++;
            return true;
        }

        return false;
    }

    public Boolean isCorrectEng(String answer) {
        if (answer.equals(active.getEngTrans())) {
            points++;
            return true;
        }
        return false;

    }


    public String getScoreAsString() {
        String results = points + "/" + maxPoints;
        return results;
    }

}

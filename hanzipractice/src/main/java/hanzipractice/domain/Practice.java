package hanzipractice.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Practice {

    private List<Word> words;
    private Word active;
    private int points;
    private int maxPoints;

    public Practice(MyList mList) {
        words = new ArrayList<>();
        words.addAll(mList.getWords());
        maxPoints = words.size();
        points = 0;
    }

    public int getSize() {
        return words.size();
    }

    public int getScore() {
        return this.points;
    }

    /**
     * fetches a random word as the next question, removes the word from the
     * list so it won't be asked again
     *
     * @return the asked word as a Word object
     */
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

    /**
     * checks if the answer given in pinyin is correct
     *
     * @param answer user inputted answer
     * @return true if correct, otherwise false
     */

    public Boolean isCorrectPinyin(String answer) {

        if (answer.equals(active.getPinyin())) {
            points++;
            return true;
        }

        return false;
    }

    /**
     * checks if the answer given in English is correct
     *
     * @param answer user inputted answer
     * @return true if correct, otherwise false
     */
    public Boolean isCorrectEng(String answer) {
        if (answer.equals(active.getEngTrans())) {
            points++;
            return true;
        }
        return false;

    }

    /**
     * returns the score as scored points out of max points
     *
     * @return points/maxpoints
     */
    public String getScoreAsString() {
        String results = points + "/" + maxPoints;
        return results;
    }

}

package hanzipractice.domain;

public class Word {

    private int id;
    private String hanzi;
    private String pinyin;
    private String engTrans;

    public Word(int id, String hanzi, String pinyin, String engTrans) {

        this.id = id;
        this.hanzi = hanzi;
        this.pinyin = pinyin;
        this.engTrans = engTrans;
    }

    public int getID() {
        return id;
    }

    public String getHanzi() {
        return hanzi;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getEngTrans() {
        return engTrans;
    }

    /**
     *
     * @return word's id, hanzi, pinyin and the English translation as a
     * formatted string
     */
    @Override
    public String toString() {
        return String.format("%2s %2s %15s %15s", id, hanzi, pinyin, engTrans);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Word)) {
            return false;
        }
        Word other = (Word) obj;
        return id == other.id;
    }

}

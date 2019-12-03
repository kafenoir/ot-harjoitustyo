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

}
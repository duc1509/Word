public class Word {
    private String word_target;
    private String word_explain;

    public String getWordTarget() {
        return this.word_target;
    }

    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    public String getWordExplain() {
        return word_explain;
    }

    public void setWordEplain(String word_explain) {
        this.word_explain = word_explain;
    }

    public Word() {
        this.word_target = " ";
        this.word_explain = " ";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    public void print() {
        System.out.println(this.word_target + " " + this.word_explain);
    }
}

package entity;

public class Answer extends Entity {
    private String textOfAnswer;
    private String answerIsTrue;
    private int questID;

    public String getTextOfAnswer() {
        return textOfAnswer;
    }

    public void setTextOfAnswer(String textOfAnswer) {
        this.textOfAnswer = textOfAnswer;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public String getAnswerIsTrue() {
        return answerIsTrue;
    }

    public void setAnswerIsTrue(String answerIsTrue) {
        this.answerIsTrue = answerIsTrue;
    }
}

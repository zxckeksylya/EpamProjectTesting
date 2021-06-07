package entity;

public class Quest extends Entity {
    private String textOfQuest;
    private int testID;

    public String getTextOfQuest() {
        return textOfQuest;
    }

    public void setTextOfQuest(String textOfQuest) {
        this.textOfQuest = textOfQuest;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }
}


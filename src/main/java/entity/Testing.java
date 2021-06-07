package entity;

public class Testing extends Entity {
    private int testID;
    private int userID;
    private int numberOfTrueQuests;

    public int getNumberOfTrueQuests() {
        return numberOfTrueQuests;
    }

    public void setNumberOfTrueQuests(int numberOfTrueQuests) {
        this.numberOfTrueQuests = numberOfTrueQuests;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

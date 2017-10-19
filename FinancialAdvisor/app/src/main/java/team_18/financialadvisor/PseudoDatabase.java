/**
*Class is used to test functionality of app prior to developing the database by acting as a surrogate for the database
*/
public class PseudoDatabase {
    private float currentBalance;
    private float expensesRemaining;
    private int weeksUsed;
    private int weeksDelinquent;
    private int weeksClose;
    private float savings;
    private int currentIndex;


    public PseudoDatabase(float currentBalance, float expensesRemaining, int weeksUsed, int weeksDelinquent, int weeksClose, float savings, int currentIndex) {
        this.currentBalance = currentBalance;
        this.expensesRemaining = expensesRemaining;
        this.weeksUsed = weeksUsed;
        this.weeksDelinquent = weeksDelinquent;
        this.weeksClose = weeksClose;
        this.savings = savings;
        this.currentIndex = currentIndex;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public float getExpensesRemaining() {
        return expensesRemaining;
    }

    public void setExpensesRemaining(float expensesRemaining) {
        this.expensesRemaining = expensesRemaining;
    }

    public int getWeeksUsed() {
        return weeksUsed;
    }

    public void setWeeksUsed(int weeksUsed) {
        this.weeksUsed = weeksUsed;
    }

    public int getWeeksDelinquent() {
        return weeksDelinquent;
    }

    public void setWeeksDelinquent(int weeksDelinquent) {
        this.weeksDelinquent = weeksDelinquent;
    }

    public int getWeeksClose() {
        return weeksClose;
    }

    public void setWeeksClose(int weeksClose) {
        this.weeksClose = weeksClose;
    }

    public float getSavings() {
        return savings;
    }

    public void setSavings(float savings) {
        this.savings = savings;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
/**
*Class is used to test functionality of app prior to developing the database by acting as a surrogate for the database
*/
public class PseudoDatabase {
    private float currentBalance;
    private float expensesRemaining;
    private int weeksUsed;
    private int weeksDelinquent;
    private int weeksClose;
    private float totalSavings;
    private int currentIndex;



    public PseudoDatabase(float balance, float expenses, int weeks, int delinquent, int close, float savings, int index) {
        currentBalance = balance;
        expensesRemaining = expenses;
        weeksUsed = weeks;
        weeksDelinquent = delinquent;
        weeksClose = close;
        totalSavings = savings;
        currentIndex = index;
    }




    //Getters and Setters
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

    public float getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(float totalSavings) {
        this.totalSavings = totalSavings;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
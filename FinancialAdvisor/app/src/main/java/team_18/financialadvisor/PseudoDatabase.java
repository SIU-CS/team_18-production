package team_18.financialadvisor;

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

    private static PseudoDatabaseEntry root;
    private static PseudoDatabaseEntry temp;
    private static PseudoDatabaseEntry current;
    private static int numOfEntries;



    public PseudoDatabase(float balance, float expenses, int weeks, int delinquent, int close, float savings, int index) {
        currentBalance = balance;
        expensesRemaining = expenses;
        weeksUsed = weeks;
        weeksDelinquent = delinquent;
        weeksClose = close;
        totalSavings = savings;
        currentIndex = index;
        numOfEntries =0;
    }

    public void newDatabaseEntry(String type, float amount, String comment, int ID){
        PseudoDatabaseEntry newEntry = new PseudoDatabaseEntry(type, amount, comment, ID);
        if(root==null){
            root=newEntry;
            root.previousEntry=null;
            root.nextEntry=null;
        }
        else{
            current=root;
            while(current.nextEntry!=null){
                current=current.nextEntry;
            }
            current.nextEntry=newEntry;
            newEntry.previousEntry=current;
            newEntry.nextEntry=null;
        }
        numOfEntries++;

    }

    public void deleteEntry(){
        int counter=1;
        current= root;
        if(numOfEntries==1){
            root= current.nextEntry;
            current.nextEntry=null;
            current.previousEntry=null;
        }
        else{
            while(counter<numOfEntries){
                current=current.nextEntry;
                counter++;
            }
            if(current.nextEntry==null){
                current.previousEntry.nextEntry=null;
                current.previousEntry=null;
            }
            else{
                current.previousEntry.nextEntry=current.nextEntry;
                current.nextEntry.previousEntry=current.previousEntry;
            }
        }
        numOfEntries--;
    }
    public boolean isEmpty(){
        return root==null;
    }
    public void clear(){
        while(!isEmpty())
            deleteEntry();
    }
    public int getNumOfEntries(){
        return numOfEntries;
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
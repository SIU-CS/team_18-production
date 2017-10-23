package team_18.financialadvisor;

public class PseudoUpcomingDatabase{
    private static int numOfEntries;
    private static PseudoUpcomingEntry root;
    private static PseudoUpcomingEntry temp;
    private static PseudoUpcomingEntry current;

    public PseudoUpcomingDatabase() {
        numOfEntries=0;
    }

    public void newUpcomingEntry(String type, float amount, String comment, int ID, String month, int date, int year){
        PseudoUpcomingEntry newEntry = new PseudoUpcomingEntry(type, amount, comment, ID, month, date, year);
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

}
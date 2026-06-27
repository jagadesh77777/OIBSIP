import java.util.ArrayList;
public class Transaction {
    private ArrayList<String> history= new ArrayList<String>();
    public void addTransaction(String transaction){
        history.add(transaction);
    }
    public void showHistory(){
        if(history.isEmpty()){
            System.out.println("No transactions found.");
            return;
        }
        System.out.println("Transaction History:");
        for(String t : history){
            System.out.println(t);
        }
    }
    
    
}

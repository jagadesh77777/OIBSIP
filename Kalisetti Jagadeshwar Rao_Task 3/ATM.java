import java.util.Scanner;

public class ATM {

    //private User user;
    private Account account;
    private Transaction transaction;
    private Scanner sc;

    public ATM(User user, Account account, Transaction transaction) {
        //this.user = user;
        this.account = account;
        this.transaction = transaction;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {

        int choice;

        do {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    transaction.showHistory();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    private void deposit() {

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.deposit(amount)) {

    transaction.addTransaction("Deposited ₹" + amount);

    System.out.println("Deposit Successful.");
    System.out.println("Current Balance: ₹" + account.getBalance());

} else {

    System.out.println("Invalid amount.");

}
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    private void withdraw() {

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.withdraw(amount)) {

            transaction.addTransaction("Withdrawn ₹" + amount);

            System.out.println("Withdrawal Successful.");
            System.out.println("Current Balance: ₹" + account.getBalance());

        } else {

            System.out.println("Insufficient Balance.");

        }
    }

    private void transfer() {

        System.out.print("Enter Receiver Account Number: ");
        String receiver = sc.next();

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (account.transfer(amount)) {

            transaction.addTransaction(
                    "Transferred ₹" + amount + " to Account " + receiver);

            System.out.println("Transfer Successful.");
            System.out.println("Current Balance: ₹" + account.getBalance());

        } else {

            System.out.println("Insufficient Balance.");

        }
    }
}
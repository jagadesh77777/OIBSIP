import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = new User("admin", "Jagadesh", "1234");
        Account account = new Account(0.0);
        Transaction transaction = new Transaction();

        System.out.println("===== ATM LOGIN =====");

        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (id.equals(user.getUserId()) && pin.equals(user.getPin())) {

            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, " + user.getName());

            ATM atm = new ATM(user, account, transaction);
            atm.showMenu();

        } else {

            System.out.println("Invalid User ID or PIN.");

        }

        sc.close();
    }
}

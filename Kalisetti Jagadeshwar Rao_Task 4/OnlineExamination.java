import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamination {
    static Scanner sc = new Scanner(System.in);

    static String username = "admin";
    static String password = "1234";
    static String profileName = "Jagadesh";

    static int score = 0;
    static boolean timeUp = false;

    public static void main(String[] args) {

        System.out.println("===== ONLINE EXAMINATION SYSTEM =====");

        if (login()) {
            int choice;

            do {
                System.out.println("\n1. Update Profile");
                System.out.println("2. Start Examination");
                System.out.println("3. Logout");
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        updateProfile();
                        break;

                    case 2:
                        startExam();
                        break;

                    case 3:
                        System.out.println("Logged Out Successfully!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 3);
        } else {
            System.out.println("Login Failed!");
        }
    }

    static boolean login() {
        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        return user.equals(username) && pass.equals(password);
    }

    static void updateProfile() {
        System.out.println("\n--- Update Profile ---");

        System.out.print("Enter New Profile Name: ");
        profileName = sc.nextLine();

        System.out.print("Enter New Password: ");
        password = sc.nextLine();

        System.out.println("Profile Updated Successfully!");
    }

    static void startExam() {

        score = 0;
        timeUp = false;

        System.out.println("\nExam Started!");
        System.out.println("Time Limit: 60 Seconds");

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                timeUp = true;
                System.out.println("\nTime Over! Auto Submitting...");
            }
        }, 60000);

        askQuestion(
                "1. Which language is platform independent?",
                "1. C", "2. Java", "3. C++", "4. Python",
                2);

        askQuestion(
                "2. JVM stands for?",
                "1. Java Variable Machine",
                "2. Java Virtual Machine",
                "3. Joint Virtual Machine",
                "4. Java Verified Machine",
                2);

        askQuestion(
                "3. Which company developed Java?",
                "1. Microsoft",
                "2. Google",
                "3. Sun Microsystems",
                "4. Oracle",
                3);

        timer.cancel();

        System.out.println("\n===== EXAM SUBMITTED =====");
        System.out.println("Score: " + score + "/3");
    }

    static void askQuestion(String question,
                            String op1,
                            String op2,
                            String op3,
                            String op4,
                            int correctAnswer) {

        if (timeUp)
            return;

        System.out.println("\n" + question);
        System.out.println(op1);
        System.out.println(op2);
        System.out.println(op3);
        System.out.println(op4);

        System.out.print("Enter Answer: ");
        int answer = sc.nextInt();

        if (answer == correctAnswer) {
            score++;
        }
    }
}


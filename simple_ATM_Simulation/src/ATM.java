
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        int balance = 5000, withdraw, deposit;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n**********BDC ATM**********");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter amount to withdraw: ");
                    withdraw = sc.nextInt();
                    if (withdraw > balance) {
                        System.out.println("Insufficient balance");
                    } else {
                        balance = balance - withdraw;
                        System.out.println("Please collect your cash");
                    }
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    deposit = sc.nextInt();
                    balance = balance + deposit;
                    break;
                case 3:
                    System.out.println("Balance: " + balance);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

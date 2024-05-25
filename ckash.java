import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static Random rand = new Random();

    public static String generateRandomString(int length) {
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(charset.length());
            randomString.append(charset.charAt(index));
        }
        return randomString.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input3, a, b, c, number, pin;
        double balance = 0; // Initialize balance
        int savedPin = 1234; // Sample PIN, you should replace it with your own logic
        double sendMoneyFee = 5;
        String reference, phn;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = dateFormat.format(new Date());

        while (true) {
            System.out.println("ckash");
            System.out.println("1.Send Money");
            System.out.println("2.Send Money to Non-ckash User");
            System.out.println("3.Mobile Recharge");
            System.out.println("4.Payment");
            System.out.println("5.Cash Out");
            System.out.println("6.Pay Bill");
            System.out.println("7.Add Money");
            System.out.println("8.Download ckash App");
            System.out.println("9.My ckash");
            System.out.println("10.Reset PIN");
            System.out.println("0.Exit");
            input3 = scanner.nextInt();

            switch (input3) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    // Send Money
                    System.out.println("Enter Receiver cKash Account No:");
                    number = scanner.nextInt();
                    System.out.println("Enter Amount:");
                    double amount = scanner.nextDouble();
                    System.out.println("Enter Reference:");
                    reference = scanner.next();
                    System.out.println("Enter Menu PIN to confirm:");
                    pin = scanner.nextInt();
                    if (pin == savedPin) {
                        if ((amount + sendMoneyFee) <= balance) {
                            balance -= (amount + sendMoneyFee);
                            System.out.printf("Send Money to TK %.2f to %d Successful. Ref %s. Fee Tk %.2f. Balance Tk %.2f.%n", amount, number, reference, sendMoneyFee, balance);
                            // Writing to file
                            try {
                                PrintWriter file = new PrintWriter(new FileWriter("statements.txt", true));
                                file.printf("%s Send Money - Tk %.2f%n", dateStr, amount);
                                file.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Insufficient balance");
                        }
                    } else {
                        System.out.println("Wrong Pin");
                    }
                    break;
                case 2:
                    // Send Money to Non-ckash User
                    // Similar logic as case 1
                    break;
                case 3:
                    // Mobile Recharge
                    // Similar logic as case 1
                    break;
                // Handle other cases similarly
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
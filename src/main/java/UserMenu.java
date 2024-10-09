
import java.util.Scanner;

/** Project: Lab3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/8/2024
 * Rev: Added To UserMenu

 */
/**
 * Provides a menu for selecting database operations.
 */
public class UserMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n===== Database Selection Menu =====");
            System.out.println("1. MySQL");
            System.out.println("2. MongoDB");
            System.out.println("3. Redis");
            System.out.println("4. Blockchain");
            System.out.println("5. Exit");
            System.out.print("Pick A Number Any Number FROM 1-5 I DARE YA: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Pick a # 1-5 Pretty PLEASEEEEEEE!");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    MySqlCRUD.performCRUDOperations();
                    break;
                case 2:
                    MongoCRUD.performCRUDOperations();
                    break;
                case 3:
                    RedisCRUD.performCRUDOperations();
                    break;
                case 4:
                    SimpleBlockchain.performBlockchainOperations();
                    break;
                case 5:
                    System.out.println("U R NOW Exiting THE CRUD LOL!");
                    break;
                default:
                    System.out.println("Pick a # 1-5 Pretty PLEASEEEEEE!");
            }
        } while (choice != 5);

        scanner.close();
    }
}

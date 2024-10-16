

/** Project: Lab3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/15/2024
 * Rev: Had to print out cust info first change!!!

 */

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Customer details!!!!
        System.out.println("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Customer First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Customer Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter Customer Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter Customer Email: ");
        String email = scanner.nextLine();

        // Create a Customer object
        Customer customer = new Customer(id, firstName, lastName, phoneNumber, email);

        // Pass the customer object to the UserMenu
        System.out.println("Customer created: " + customer);


        UserMenu.main(args);
    }
}

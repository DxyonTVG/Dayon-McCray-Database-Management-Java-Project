

import redis.clients.jedis.Jedis;
import java.util.Scanner;
import java.util.Set;

/** Project: Lab3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/8/2024
 * Rev: Added To UserMenu

 */

/**
 * RedisCRUD to (CRUD) operations on customer data stored in Redis.
 */
public class RedisCRUD {

    /**
     * Starts the CRUD operations menu for managing customers.
     */
    public static void performCRUDOperations() {
        Scanner scanner = new Scanner(System.in);
        Jedis jedis = new Jedis("redis://localhost:6379");

        while (true) {
            System.out.println("\n===== Redis CRUD Operations =====");
            System.out.println("1. Create Customer");
            System.out.println("2. Read All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCustomer(scanner, jedis);
                    break;
                case 2:
                    readAllCustomers(jedis);
                    break;
                case 3:
                    updateCustomer(scanner, jedis);
                    break;
                case 4:
                    deleteCustomer(scanner, jedis);
                    break;
                case 5:
                    System.out.println("Exiting Redis CRUD operations...");
                    jedis.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
            }
        }
    }

    /**
     * Creates a new customer in Redis.
     *
     * @param scanner Scanner object for user input.
     * @param jedis Jedis instance for interacting with Redis.
     */
    private static void createCustomer(Scanner scanner, Jedis jedis) {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        jedis.hset("customer:" + id, "firstName", firstName);
        jedis.hset("customer:" + id, "lastName", lastName);
        jedis.hset("customer:" + id, "phoneNumber", phoneNumber);
        jedis.hset("customer:" + id, "email", email);
        System.out.println("Customer added successfully.");
    }

    /**
     * Displays all customers from Redis.
     *
     * @param jedis Jedis instance for interacting with Redis.
     */
    private static void readAllCustomers(Jedis jedis) {
        Set<String> keys = jedis.keys("customer:*");
        if (keys.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("Customers in the database:");
        for (String key : keys) {
            System.out.println("Customer ID: " + key.replace("customer:", ""));
            System.out.println("First Name: " + jedis.hget(key, "firstName"));
            System.out.println("Last Name: " + jedis.hget(key, "lastName"));
            System.out.println("Phone Number: " + jedis.hget(key, "phoneNumber"));
            System.out.println("Email: " + jedis.hget(key, "email"));
            System.out.println("------------------------------");
        }
    }

    /**
     * Updates Customer's information in Redis.
     *
     * @param scanner Scanner object for user input.
     * @param jedis Jedis instance for interacting with Redis.
     */
    private static void updateCustomer(Scanner scanner, Jedis jedis) {
        System.out.print("Enter customer ID to update: ");
        String updateId = scanner.nextLine();

        if (jedis.exists("customer:" + updateId)) {
            System.out.println("Customer Information:");
            System.out.println("First Name: " + jedis.hget("customer:" + updateId, "firstName"));
            System.out.println("Last Name: " + jedis.hget("customer:" + updateId, "lastName"));
            System.out.println("Phone Number: " + jedis.hget("customer:" + updateId, "phoneNumber"));
            System.out.println("Email: " + jedis.hget("customer:" + updateId, "email"));
            System.out.println("Which field do you want to update?");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Phone Number");
            System.out.println("4. Email");
            System.out.print("Choose an option (1-4): ");
            int fieldChoice = scanner.nextInt();
            scanner.nextLine();

            switch (fieldChoice) {
                case 1:
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    jedis.hset("customer:" + updateId, "firstName", newFirstName);
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    jedis.hset("customer:" + updateId, "lastName", newLastName);
                    break;
                case 3:
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    jedis.hset("customer:" + updateId, "phoneNumber", newPhoneNumber);
                    break;
                case 4:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    jedis.hset("customer:" + updateId, "email", newEmail);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
                    return;
            }
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    /**
     * Deletes a customer from Redis.
     *
     * @param scanner Scanner object for user input.
     * @param jedis Jedis instance for interacting with Redis.
     */
    private static void deleteCustomer(Scanner scanner, Jedis jedis) {
        System.out.print("Enter customer ID to delete: ");
        String deleteId = scanner.nextLine();

        if (jedis.exists("customer:" + deleteId)) {
            jedis.del("customer:" + deleteId);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }
}

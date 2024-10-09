

/**
 * Project: Lab3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/8/2024
 * Rev: Make Sure Code Works Add To UserMenu
 */

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MongoCRUD {

    /**
     * Performs CRUD operations the MongoDB database.
     * Connects to the MongoDB and gives a menu for managing customers.
     */
    public static void performCRUDOperations() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("your_database_name");
            MongoCollection<Document> collection = database.getCollection("customers");
            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            do {
                System.out.println("===== Customer Management Menu =====");
                System.out.println("1. Insert Customer");
                System.out.println("2. Read Customers");
                System.out.println("3. Update Customer");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");
                System.out.print("Please choose an option (1-5): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            insertCustomer(scanner, collection);
                            break;
                        case 2:
                            readCustomers(collection);
                            break;
                        case 3:
                            updateCustomer(scanner, collection);
                            break;
                        case 4:
                            deleteCustomer(scanner, collection);
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Please enter a valid integer.");
                    scanner.next();
                }
            } while (choice != 5);
        }
    }

    /**
     * Inserts a new customer document into the MongoDB collection.
     *
     * @param scanner The scanner to capture user input.
     * @param collection The MongoDB collection is where the customer document will be.
     */
    private static void insertCustomer(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Document newCustomer = new Document("id", id)
                .append("first_name", firstName)
                .append("last_name", lastName)
                .append("phone_number", phoneNumber)
                .append("email", email);

        collection.insertOne(newCustomer);
        System.out.println("Customer added successfully!");
    }

    /**
     * Reads all customer documents from the MongoDB collection and displays them.
     *
     * @param collection Read the customer documents from.
     */
    private static void readCustomers(MongoCollection<Document> collection) {
        System.out.println("Customers in the database:");
        FindIterable<Document> customerDocuments = collection.find();
        List<Customer> customers = new ArrayList<>();

        for (Document doc : customerDocuments) {
            Integer docId = doc.getInteger("id");
            String docFirstName = doc.getString("first_name");
            String docLastName = doc.getString("last_name");
            String docPhoneNumber = doc.getString("phone_number");
            String docEmail = doc.getString("email");

            customers.add(new Customer(
                    docId != null ? docId : 0,
                    docFirstName != null ? docFirstName : "N/A",
                    docLastName != null ? docLastName : "N/A",
                    docPhoneNumber != null ? docPhoneNumber : "N/A",
                    docEmail != null ? docEmail : "N/A"
            ));
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * Updates a specific customer document in the MongoDB collection based on the ID.
     *
     *
     * @param collection  Update Customer
     */
    private static void updateCustomer(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter Customer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Document customerDocument = collection.find(Filters.eq("id", id)).first();
        if (customerDocument == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("What would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Email");
        System.out.print("Enter your choice (1-4): ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                String newFirstName = scanner.nextLine();
                collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("first_name", newFirstName)));
                System.out.println("First Name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                String newLastName = scanner.nextLine();
                collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("last_name", newLastName)));
                System.out.println("Last Name updated successfully!");
                break;
            case 3:
                System.out.print("Enter new Phone Number: ");
                String newPhoneNumber = scanner.nextLine();
                collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("phone_number", newPhoneNumber)));
                System.out.println("Phone Number updated successfully!");
                break;
            case 4:
                System.out.print("Enter new Email: ");
                String newEmail = scanner.nextLine();
                collection.updateOne(Filters.eq("id", id), new Document("$set", new Document("email", newEmail)));
                System.out.println("Email updated successfully!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Deletes a specific customer document from the MongoDB collection based on the user-provided ID.
     *
     *
     * @param collection Delete the customer
     */
    private static void deleteCustomer(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter Customer ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Document customerDocument = collection.find(Filters.eq("id", id)).first();
        if (customerDocument != null) {
            collection.deleteOne(Filters.eq("id", id));
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }
}

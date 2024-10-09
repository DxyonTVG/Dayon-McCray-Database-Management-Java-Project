

/** Project: Lab 3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/8/2024
 * Rev: Make Sure Code Work Add To UserMenu

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides methods for performing CRUD operations on a MySQL database.
 */

public class MySqlCRUD {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/CustomerDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST88";

    /**
     * The main method to initiate the CRUD operations.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        performCRUDOperations();
    }
    /**
     * Prompts the user for CRUD operations and executes the selected operation.
     */
    public static void performCRUDOperations() {
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            int choice;
            do {
                System.out.println("Menu:");
                System.out.println("1. Add Customer");
                System.out.println("2. View Customers");
                System.out.println("3. Update Customer");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addCustomer(scanner, connection);
                        break;
                    case 2:
                        viewCustomers(connection);
                        break;
                    case 3:
                        System.out.print("Enter Customer ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        updateCustomer(connection, updateId, scanner);
                        break;
                    case 4:
                        System.out.print("Enter Customer ID to delete: ");
                        int deleteId = scanner.nextInt();
                        deleteCustomer(connection, deleteId);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner
        }
    }
    /**
     * Adds a new customer to the database.
     *
     * @param scanner    Scanner object for user input.
     * @param connection Connect to Db.
     * @throws SQLException if error occurs.
     */
    private static void addCustomer(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter Customer ID: ");
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
        insertCustomer(connection, id, firstName, lastName, phoneNumber, email);
    }
    /**
     * Inserts a new customer record into the database.
     *
     * @param connection Database connection object.
     * @param id The ID
     * @param firstName first name.
     * @param lastName  Customer's last name.
     * @param phoneNumber Customer's phone number.
     * @param email     Customer's email address.
     * @throwsSQLException if a database access error occurs.
     */
    private static void insertCustomer(Connection connection, int id, String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        String sql = "INSERT INTO Customers (id, firstName, lastName, phoneNumber, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
            System.out.println("Customer added successfully!");
        }
    }
    /**
     * Retrieves and displays all customers from the database.
     *
     * @param connection Database connection object.
     * @throws SQLException if a database access error occurs.
     */
    private static void viewCustomers(Connection connection) throws SQLException {
        List<Customer> customers = getAllCustomers(connection);
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
    /**
     * Gets all customers from the database.
     *
     * @param connection Database connection object.
     * @return A list of customers.
     * @throws SQLException if a database access error occurs.
     */
    private static List<Customer> getAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, firstName, lastName, phoneNumber, email FROM Customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                customers.add(new Customer(id, firstName, lastName, phoneNumber, email));
            }
        }
        return customers;
    }
    /**
     * Updates the details of an existing customer.
     *
     * @param connection Database connection object.
     * @param id        Customer ID to be updated.
     * @param scanner   Scanner object for user input.
     * @throws SQLException if a database access error occurs.
     */
    private static void updateCustomer(Connection connection, int id, Scanner scanner) throws SQLException {
        System.out.println("What would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Email");
        System.out.print("Enter your choice (1-4): ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "";
        switch (updateChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                String newFirstName = scanner.nextLine();
                sql = "UPDATE Customers SET firstName = ? WHERE id = ?";
                updateChoice(connection, sql, newFirstName, id);
                System.out.println("First Name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                String newLastName = scanner.nextLine();
                sql = "UPDATE Customers SET lastName = ? WHERE id = ?";
                updateChoice(connection, sql, newLastName, id);
                System.out.println("Last Name updated successfully!");
                break;
            case 3:
                System.out.print("Enter new Phone Number: ");
                String newPhoneNumber = scanner.nextLine();
                sql = "UPDATE Customers SET phoneNumber = ? WHERE id = ?";
                updateChoice(connection, sql, newPhoneNumber, id);
                System.out.println("Phone Number updated successfully!");
                break;
            case 4:
                System.out.print("Enter new Email: ");
                String newEmail = scanner.nextLine();
                sql = "UPDATE Customers SET email = ? WHERE id = ?";
                updateChoice(connection, sql, newEmail, id);
                System.out.println("Email updated successfully!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    /**
     * Deletes a customer from the database by ID.
     *
     * @param connection Database connection object.
     * @param id        Customer ID to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    private static void deleteCustomer(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Customer with ID " + id + " not found.");
            }
        }
    }
    /**
     * Updates a customer's specific field in the database.
     *
     * @param connection Database connection object.
     * @param sql       SQL update statement.
     * @param value     New value to set.
     * @param id        Customer ID to be updated.
     * @throws SQLException if a database access error occurs.
     */
    private static void updateChoice(Connection connection, String sql, Object value, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (value instanceof String) {
                preparedStatement.setString(1, (String) value);
            } else if (value instanceof Integer) {
                preparedStatement.setInt(1, (Integer) value);
            }
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }
}


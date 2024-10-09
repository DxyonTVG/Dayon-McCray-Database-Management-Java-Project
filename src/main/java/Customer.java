/**
 * Project: Lab3
 * Purpose Details: Database Assignment
 * Course: IST 242
 * Author: Dayon McCray
 * Date Developed: 10/8/2024
 * Last Date Changed: 10/8/2024
 * Rev: Make Sure Code Works Add To UserMenu
 */

public class Customer {
    /**
     * The ID for The Customer
     */
    private int id;

    /**
     * Customer First Name
     */
    private String firstName;

    /**
     * Customer Last Name
     */
    private String lastName;

    /**
     * Customer Phone Number
     */
    private String phoneNumber;

    /**
     * Customer Email
     */
    private String email;

    /**
     * Constructor to create a new Customer object.
     *
     * @param id The unique id for the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param email The email address of the customer.
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Gets the customer's ID.
     *
     * @return The unique id for the customer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the customer's first name.
     *
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName The first name of the customer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the customer's last name.
     *
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     *
     * @param lastName The last name of the customer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return The phone number of the customer.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phoneNumber The phone number of the customer.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the customer's email address.
     *
     * @return The email address of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email The email address of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string of the customer object.
     *
     * @return A string that describes the customer with all attributes.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

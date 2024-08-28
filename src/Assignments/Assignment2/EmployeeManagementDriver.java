package Assignments.Assignment2;
// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim

import java.util.Scanner;

public class EmployeeManagementDriver {
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n" + menu);
            int choice = promptUserForInteger("Enter your choice: ");

            switch (choice) {
                case addNewEmployee:
                    handleAddNewEmployee();
                    break;
                    
                case deleteEmployee:
                    handleDeleteEmployee();
                    break;
                    
                case displayEmployees:
                    employeeList.displayEmployees();
                    break;
                    
                case exit:
                    System.out.println("Exiting...");
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prompts user to enter the employee ID of the employee to be deleted, then calls the
     * deleteEmployee method on the employee list with that ID, then prints a confirmation response.
     */
    private static void handleDeleteEmployee() {
        int empID = promptUserForInteger("Please enter the employee ID number of the employee to delete: ");
        employeeList.deleteEmployee(empID);
        System.out.println("Employees with ID number " + empID + " have been removed from the list.");
    }

    /**
     * Prompts user to enter the name, position, and salary of the new employee, then
     * initializes the new employee with this information. Then it calls the addEmployee method
     * on the employee list to add the new employee to the list. Finally, it prints a confirmation response indicating
     * their assigned employee ID.
     */
    private static void handleAddNewEmployee() {
        String name = promptUserForString("Please enter the name of the new employee: ");
        String position = promptUserForString("Please enter the job position of the new employee: ");
        double salary = promptUserForPositiveDouble("Please enter the salary of the new employee: $");
        Employee newEmployee = new Employee(name, position, salary);
        employeeList.addEmployee(newEmployee);
        System.out.println(name + " in job position of " + position + " with a salary of $" + newEmployee.getSalary() +
                " was added with employee ID number " + newEmployee.getEmployeeID() + ".");
    }

    /**
     * Displays the given prompt message and then gets a valid integer from the user.
     *
     * @param prompt message to be displayed to the user before waiting for an integer input.
     * @return the integer entered by the user.
     */
    private static int promptUserForInteger(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception E) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return input;
    }

    /**
     * Displays the given prompt message and then gets a valid positive double from the user.
     *
     * @param prompt message to be displayed to the user before waiting for a positive double input.
     * @return the double entered by the user.
     */
    private static double promptUserForPositiveDouble(String prompt) {
        double input = 0.0d;
        while (true) {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(scanner.nextLine());
                if (input <= 0) { //assignment asks us to ensure the double is strictly positive.
                    System.out.println("Invalid input, salary must be positive number.");
                }
                else {
                    return input;
                }
            } catch(Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    /**
     * Prompts the user with the provided string then gets a string input from the user.
     * @param prompt message to be displayed to the user before waiting for a string input.
     * @return the string entered by the user.
     */
    public static String promptUserForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    //the following variables are declared and placed here to increase the readability of the main method
    public static final String menu = """
            Employee Management Software:
            1) Add a new employee
            2) Delete an employee
            3) Display all employees
            0) Exit""";
    static Scanner scanner = new Scanner(System.in);
    static EmployeeLinkedList employeeList = new EmployeeLinkedList();
    static final int addNewEmployee = 1;
    static final int deleteEmployee = 2;
    static final int displayEmployees = 3;
    static final int exit = 0;
}

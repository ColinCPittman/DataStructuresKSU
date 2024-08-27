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
                case 1:
                    String name = promptUserForString("Please enter the name of the new employee: ");
                    String position = promptUserForString("Please enter the job position of the new employee: ");
                    double salary = promptUserForPositiveDouble("Please enter the salary of the new employee: $");
                    Employee newEmployee = new Employee(name, position, salary);
                    employeeList.addEmployee(newEmployee);
                    System.out.println(name + " in job position of " + position + " with a salary of $" + newEmployee.getSalary() +
                            " was added with employee ID number " + newEmployee.getEmployeeID() + ".");

                    break;
                case 2:
                    int empID = promptUserForInteger("Please enter the employee ID number of the employee to delete: ");
                    employeeList.deleteEmployee(empID);
                    System.out.println("Employees with ID number " + empID + " have been removed from the list.");
                    break;
                case 3:
                    employeeList.displayEmployees();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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
                input = Integer.parseInt(sc.nextLine());
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
                input = Double.parseDouble(sc.nextLine());
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
     * @param prompt message to be displayed to the user before waiting for a string input.
     * @return
     */
    public static String promptUserForString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static final String menu = """
            Employee Management Software:
            1) Add a new employee
            2) Delete an employee
            3) Display all employees
            0) Exit""";
    static Scanner sc = new Scanner(System.in);
    static EmployeeLinkedList employeeList = new EmployeeLinkedList();
}

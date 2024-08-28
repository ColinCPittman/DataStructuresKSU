package Assignments.Assignment2;
// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim
public class Employee {
    private static int nextEmployeeID = 1;
    private int employeeID;
    private String name;
    private String position;
    private double salary;

    /**
     * Instantiates a new employee with the provided values. EmployeeID is automatically assigned
     * via a static variable to ensure each employee has a unique ID.
     *
     * @param name name of the new employee.
     * @param position job position of the new employee.
     * @param salary salary of the new employee.
     */
    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.employeeID = nextEmployeeID++;
    }

    public int getEmployeeID() {
        return employeeID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-15s $%-10.2f", employeeID, name, position, salary);
    }
}
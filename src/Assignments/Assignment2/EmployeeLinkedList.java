package Assignments.Assignment2;

import java.util.Iterator;
import java.util.function.Consumer;

// Name: Colin Pittman
// Class: CS 3305/Section 4
// Term: Fall 2024
// Instructor: Umama Tasnim
public class EmployeeLinkedList{
    private class Node {
        Employee employee;
        Node next;

        /**
         * Creates a new node containing the provided employee and assigns the provided node as this
         * node's 'next' node.
         *
         * @param employee employee element to be added.
         * @param next     the node which this node will point to as its 'next' node.
         */
        public Node(Employee employee, Node next) {
            this.employee = employee;
            this.next = next;
        }

        /**
         * Creates a new node containing the provided employee with this node's 'next' node is initialized to null.
         *
         * @param employee employee element to be added.
         */
        public Node(Employee employee) {
            this.employee = employee;
            this.next = null;
        }
    }

    Node head;

    /**
     * @param head Node to be inserted as the head of the new list.
     */
    public EmployeeLinkedList(Node head) {
        this.head = head;
    }

    /**
     * Initializes a new list with no employee nodes.
     */
    public EmployeeLinkedList() {
        this.head = null;
    }

    /**
     * Adds the provided employee to the head of the list.
     *
     * @param emp Employee object to be added to the list.
     */
    void addEmployee(Employee emp) {
        if (head == null) {
            head = new Node(emp);
        } else {
            Node newNode = new Node(emp, head);
            head = newNode;
        }
    }

    /**
     * Traverses the list and removes any elements from the list where the employee ID matches the employeeID parameter.
     *
     * @param employeeID employee ID of employee to be removed from the list.
     */
    void deleteEmployee(int employeeID) {

        //I would love for this to at least return a boolean to indicate if a deletion occurred, but we are instructed to make this a void
        //Since this method can't give feedback, I have opted to make the function of this method to be effectively taking the intersection of original list
        //and a potential version of the list excluding any employees matching the provided employee ID. Turing this method into a means of ensuring
        //an employee of the provided ID is not in the list, but not necessarily a means of deleting a specific occurrence of a node.
        //This is an intentional decision despite the fact that employeeID uniqueness is technically enforced and for the purposes of this
        //assignment it shouldn't come up, I still feel as though it's a solid implementation in principle.

        if (head == null) {
            return;
        }

        if (head.employee.getEmployeeID() == employeeID) {
            head = head.next;
            return;
        }

        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.employee.getEmployeeID() == employeeID) {
                previous.next = current.next;
            }
            previous = previous.next;
            current = current.next;
        }
    }

    /**
     * Displays and formats employee information in table format for each employee in this list.
     */
    void displayEmployees() {
        System.out.println("\nEmployee List:");
        System.out.printf("%-5s %-10s %-20s %-15s %-10s%n", "Line", "EmpID", "Name", "Position", "Salary");
        if (head == null) {
            return;
        }
        int i = 1;
        Node current = head;
        while (current != null) {
            System.out.printf("%-5d %s%n", i++, current.employee);
            current = current.next;
        }
    }

}

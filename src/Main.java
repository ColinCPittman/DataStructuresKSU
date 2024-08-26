import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println();
        while (true) {
            System.out.println(menu + list.toString());
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter element to add at the beginning: ");
                    int firstElement = scanner.nextInt();
                    list.addFirst(firstElement);
                    System.out.println("Added " + firstElement + " at the beginning.");
                    break;
                case 2:
                    System.out.print("Enter element to add at the end: ");
                    int lastElement = scanner.nextInt();
                    list.addLast(lastElement);
                    System.out.println("Added " + lastElement + " at the end.");
                    break;
                case 3:
                    try {
                        System.out.println("First element: " + list.getFirst());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Last element: " + list.getLast());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Removed first element: " + list.removeFirst());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Removed last element: " + list.removeLast());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Enter index to get element: ");
                    int indexToGet = scanner.nextInt();
                    try {
                        System.out.println("Element at index " + indexToGet + ": " + list.get(indexToGet));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.print("Enter index to add element: ");
                    int indexToAdd = scanner.nextInt();
                    System.out.print("Enter element to add: ");
                    int elementToAdd = scanner.nextInt();
                    try {
                        list.add(indexToAdd, elementToAdd);
                        System.out.println("Added element " + elementToAdd + " at index " + indexToAdd);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.print("Enter index to remove element: ");
                    int indexToRemove = scanner.nextInt();
                    try {
                        System.out.println("Removed element: " + list.remove(indexToRemove));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    System.out.print("Enter index to set element: ");
                    int indexToSet = scanner.nextInt();
                    System.out.print("Enter new element: ");
                    int elementToSet = scanner.nextInt();
                    try {
                        System.out.println("Replaced element at index " + indexToSet + " with " + elementToSet + ". Old element was " + list.set(indexToSet, elementToSet));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    System.out.print("Enter element to check if it exists: ");
                    int elementToCheck = scanner.nextInt();
                    if (list.contains(elementToCheck)) {
                        System.out.println("List contains " + elementToCheck);
                    } else {
                        System.out.println("List does not contain " + elementToCheck);
                    }
                    break;
                case 12:
                    System.out.print("Enter element to find its first index: ");
                    int elementForFirstIndex = scanner.nextInt();
                    System.out.println("First index of element " + elementForFirstIndex + ": " + list.indexOf(elementForFirstIndex));
                    break;
                case 13:
                    System.out.print("Enter element to find its last index: ");
                    int elementForLastIndex = scanner.nextInt();
                    System.out.println("Last index of element " + elementForLastIndex + ": " + list.lastIndexOf(elementForLastIndex));
                    break;
                case 14:
                    MyLinkedList<Integer> copiedList = list.copyList();
                    System.out.print("Copied List: ");
                    copiedList.forEach(e -> System.out.print(e + " "));
                    System.out.println();
                    break;
                case 15:
                    list.clear();
                    System.out.println("List cleared.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();


    }

    }
    static Scanner scanner = new Scanner(System.in);
    static MyLinkedList<Integer> list = new MyLinkedList<>();
    static final String menu = """
                Linked List Function Testing
                1. Add First\t\t\t\t9. Remove Element at Index
                2. Add Last\t\t\t\t\t10. Set Element at Index
                3. Get First\t\t\t\t11. Check if List Contains Element
                4. Get Last\t\t\t\t\t12. Get Index of First Occurrence
                5. Remove First\t\t\t\t13. Get Index of Last Occurrence
                6. Remove Last\t\t\t\t14. Copy List
                7. Get Element by Index\t\t15. Clear List
                8. Add Element at Index\t\t0. Exit\n
                Current List Contents: """;
}

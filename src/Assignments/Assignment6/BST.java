package Assignments.Assignment6;

import java.util.*;

public class BST {
    public static final String menu = """     
                
            -----MAIN MENU-----
            1. Insert a value
            2. Display traversals
            3. Show height, min, max, and check balance
            4. Search for a value
            5. Delete a value
            6. Print all paths
            7. Exit program
            """;
    //constants for the easier readability in the switch statement of the main methods
    static final int insert = 1;
    static final int displayTraversals = 2;
    static final int showProperties = 3;
    static final int search = 4;
    static final int delete = 5;
    static final int printPaths = 6;
    static final int exit = 7;
    private static final Scanner scanner = new Scanner(System.in);
    private Node root;

    /**
     * Displays the given prompt message and then gets a valid integer from the user.
     *
     * @param prompt The message to display to the user.
     * @return The integer entered by the user.
     */
    public static int promptUserForInteger(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a valid integer.");
            }
        }
        return input;
    }

    // I did the insert method below (and others) iteratively because this was before I got clarification from professor
    // that helper methods can be used. So there is a mix of recursive and non-recursive methods in this class.
    public void insert(int value) {

        if (root == null) {
            root = new Node(value);
        }

        Node current = root;
        Node previous = null; //this is the node we are adding to since current will be null after next while loop

        while (current != null) {
            previous = current;

            if (value > current.value) {
                current = current.right;
            } else if (value < current.value) {
                current = current.left;
            } else {
                System.out.println("Error: The value '" + value + "' is already in the tree.");
                return;
            }
        }

        //adding the node
        if (value > previous.value) {
            previous.right = new Node(value);
        } else {
            previous.left = new Node(value);
        }

    }

    /**
     * Performs a binary search on the tree. O(logn) time for balanced trees.
     * Returns true if it finds the value in the tree; else false.
     *
     * @param value to be searched for
     * @return true of the value is present in the tree
     */
    public boolean search(int value) {

        if (root == null) return false;

        Node current = root;

        while (current != null) {

            if (current.value == value) {
                return true;
            } else if (value > current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    /**
     * Preforms a binary deletion of the value if it is present in the tree.
     * This follows slide 22 of Chapter 25_Trees, which outlines the three cases for deletion from BST.
     * Copies the smallest value in the right subtree.
     *
     * @param value in the tree to be searched for and deleted.
     */
    public void delete(int value) {
        if (root == null) return;

        Node nodeToDelete = root;
        Node previousNode = null;

        //find target node

        while (nodeToDelete != null && nodeToDelete.value != value) {
            previousNode = nodeToDelete;
            if (value < nodeToDelete.value) {
                nodeToDelete = nodeToDelete.left;
            } else {
                nodeToDelete = nodeToDelete.right;
            }
        }

        if (nodeToDelete == null) return; //reached the end of the tree and value to be deleted wasn't found, so there's nothing to delete.

        //case 1, leaf node

        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (nodeToDelete == root) {
                root = null;
            } else if (previousNode.left == nodeToDelete) {
                previousNode.left = null;
            } else {
                previousNode.right = null;
            }
        }

        //case 2, has 1 subtree

        else if (nodeToDelete.left == null || nodeToDelete.right == null) { //this OR statement because we already eliminated the possibility that both are null.
            Node onlyChild = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;

            if (nodeToDelete == root) { //if the while loop above never executed (because the root value matched), then nodeToDelete is still on root.
                root = onlyChild;
            } else if (previousNode.left == nodeToDelete) {
                previousNode.left = onlyChild;
            } else {
                previousNode.right = onlyChild;
            }
        }

        //case 3.2, has 2 subtrees, smallest-value-in-right-subtree approach

        else {

            //similar to the first while loop, iterate to the leftmost leaf of the nodeToDelete's right subtree
            Node smallestDescendantPrev = nodeToDelete;
            Node smallestDescendant = nodeToDelete.right;

            while (smallestDescendant.left != null) {
                smallestDescendantPrev = smallestDescendant;
                smallestDescendant = smallestDescendant.left;
            }

            //copy the smallest descendant in right subtree to the value in the node we are deleting (overwriting in this case)
            nodeToDelete.value = smallestDescendant.value;


            // Now to delete the smallest descendant, done by bypassing (unlinking) it and connecting its leftover potential larger subtree to its immediate ancestor.
            if (smallestDescendantPrev.left == smallestDescendant) { //this will always be the case with one exception covered in the else statement
                smallestDescendantPrev.left = smallestDescendant.right;
            }
            else { //smallestDescendantPrev.right is the smallest descendant, only occurs when the node being deleted is immediately to the right of root.
                smallestDescendantPrev.right = smallestDescendant.right;
            }
        }
    }
    // I talked with and emailed the professor to confirm, these recursive methods are because I preferred to do these
    // the recursive way, this lets the required functions that we had to define (as null with no parameters) to still work with this approach.
    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.value + " ");
            inorderHelper(node.right);
        }
    }

    private void preorderHelper(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderHelper(node.left);
            preorderHelper(node.right);
        }
    }

    private void postorderHelper(Node node) {
        if (node != null) {
            postorderHelper(node.left);
            postorderHelper(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void inorderTraversal() {
        inorderHelper(root);
    }

    public void preorderTraversal() {
        preorderHelper(root);
    }

    public void postorderTraversal() {
        postorderHelper(root);
    }

    // I got stuck on this one trying to do it recursively but couldn't get it working
    // ended up having to follow https://www.geeksforgeeks.org/level-order-tree-traversal/ for implementation
    // I picked the queue implementation mentioned on that page due to it having the better time complexity
    public List<Integer> levelOrderTraversal() {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.value);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }

    /**
     * Recursive helper method for the height function.
     *
     * @param node to be assessed for height
     * @return height of the node
     */
    private int heightHelper(Node node) {
        if (node == null) return -1; //-1 so a leaf node returns a height of one when combined with the +1 in the recursive calls.

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    public int height() {
        return root != null ? heightHelper(root) : 0;
    }

    public int minValue() {
        if (root == null) throw new NoSuchElementException("Tree is empty");

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public int maxValue() {
        if (root == null) throw new NoSuchElementException("Tree is empty");

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public boolean isBalanced() {
        return root != null ? isBalancedHelper(root) : true;
    }

    /**
     * @param node root to be assessed for balance
     * @return false if left height minus right height of a node is not in this set: {-1, 0 , 1}
     */
    private boolean isBalancedHelper(Node node) {
        if (node == null) return true;

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        if (Math.abs(leftHeight - rightHeight) >= 2) {
            return false;
        }

        return isBalancedHelper(node.left) && isBalancedHelper(node.right);
    }

    public void printPaths() {
        if (root == null) return;

        List<Integer> leaves = levelOrderTraversal(); //using this list to iterate through the leaves in a for each loop

        for (Integer value : leaves) {
            System.out.print("Path from root '" + root.value + "' to '" + value + "': ");
            Node current = root;

            //Binary search for the value and print the numbers along the way.
            while (current != null) {
                System.out.print(current.value + " ");
                if (current.value == value) {
                    break;
                } else if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            System.out.println();
        }
    }

    private class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        BST bst = new BST();
        while (true) {
            System.out.println(menu);
            int choice = promptUserForInteger("Enter option number: ");
            switch (choice) {
                case insert:
                    int valueToInsert = promptUserForInteger("Enter value to insert: ");
                    bst.insert(valueToInsert);
                    break;

                case displayTraversals:
                    System.out.print("\nInorder Traversal: ");
                    bst.inorderTraversal();

                    System.out.print("\nPreorder Traversal: ");
                    bst.preorderTraversal();

                    System.out.print("\nPostorder Traversal: ");
                    bst.postorderTraversal();

                    System.out.println("\nLevel Order Traversal: " + bst.levelOrderTraversal());
                    break;

                case showProperties:
                    System.out.println("Height: " + bst.height());
                    System.out.print("Minimum value: ");
                    try { //try catches here because I didn't want to return a number when there are no elements in the tree, it throws a no-such-element exception instead.
                        System.out.println(bst.minValue());
                    } catch (NoSuchElementException E) {
                        System.out.println(E.getMessage());
                    }
                    try {
                        System.out.println("Maximum value: " + bst.maxValue());
                    }catch (NoSuchElementException E) {
                        System.out.println(E.getMessage());
                    }
                    System.out.println("Tree is Balanced? " + bst.isBalanced());
                    break;

                case search:
                    int valueToSearch = promptUserForInteger("Enter value to search: ");

                    if (bst.search(valueToSearch)) {
                        System.out.println("Value " + valueToSearch + " is in the BST.");
                    } else {
                        System.out.println("Value " + valueToSearch + " is not in the BST.");
                    }
                    break;

                case delete:
                    int valueToDelete = promptUserForInteger("Enter value to delete: ");
                    if(bst.search(valueToDelete)) { //this search is just to be able to give feedback about whether it was deleted or not since delete() doesn't return boolean.
                        bst.delete(valueToDelete);
                        System.out.println("Value " + valueToDelete + " has been deleted from the BST.");
                    } else {
                        System.out.println("No matching value in the tree to delete.");
                    }

                    break;

                case printPaths:
                    bst.printPaths();
                    break;

                case exit:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}

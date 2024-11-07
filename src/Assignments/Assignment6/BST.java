package Assignments.Assignment6;

import java.util.*;

public class BST {
    private class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    static final int insert = 1;
    static final int displayTraversals = 2;
    static final int showProperties = 3;
    static final int search = 4;
    static final int delete = 5;
    static final int printPaths = 6;
    static final int exit = 7;

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
    private static Scanner scanner = new Scanner(System.in);
    private Node root;

    public void insert(int value) { //this method is instructed to not take a node as a parameter, suggesting an iterative approach, rather than recursive.

        if(root == null) {
            root = new Node(value);
        }

        Node current = root;
        Node previous = null;

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

        if(value > previous.value) {
            previous.right = new Node(value);
        } else {
            previous.left = new Node(value);
        }

    }

    public boolean search(int value) {

        if(root == null) return false;

        Node current = root;

        while(current != null) {

            if (current.value == value) {
                return true;
            } else if(value > current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    public void delete(int value) {
        if (root == null) return;

        Node current = root;
        Node previous = null;

        //find target node

        while (current != null && current.value != value) {
            previous = current;
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return;

        //case 1, leaf node

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (previous.left == current) {
                previous.left = null;
            } else {
                previous.right = null;
            }
        }

        //case 2, has 1 subtree

        else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;

            if (current == root) {
                root = child;
            } else if (previous.left == current) {
                previous.left = child;
            } else {
                previous.right = child;
            }
        }

        //case 3.2, has 2 subtrees, smallest-value-in-right-subtree approach

        else {

            Node smallestDescendantPrev = current;
            Node smallestDescendant = current.right;

            while (smallestDescendant.left != null) {
                smallestDescendantPrev = smallestDescendant;
                smallestDescendant = smallestDescendant.left;
            }

            //copy node y to node x that we are deleting
            current.value = smallestDescendant.value;

            //smallest descendant could be either left or right child of previous
            //identify it and bypass the smallest descendant by linking it with its potential larger child
            if (smallestDescendantPrev.left == smallestDescendant) {
                smallestDescendantPrev.left = smallestDescendant.right;
            } else {
                smallestDescendantPrev.right = smallestDescendant.right;
            }
        }
    }
    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.value + " ");
            inorderHelper(node.right);
        }
    }
    private void preorderHelper(Node node) {
        if(node != null) {
            System.out.println(node.value);
            preorderHelper(node.left);
            preorderHelper(node.right);
        }
    }
    private void postorderHelper(Node node) {
        if(node != null) {
            postorderHelper(node.left);
            postorderHelper(node.right);
            System.out.println(node.value);
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

    //I got stuck on this one trying to do it recursively but couldn't get it working
    //ended up having to follow https://www.geeksforgeeks.org/level-order-tree-traversal/ for implementation
    //I picked the queue implementation mentioned on that page due to it having the better time complexity
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
     * Recursive helper method for the height function
     * @param node to be assessed for height
     * @return height of the node
     */
    private int heightHelper(Node node) {
        if(node != null) return -1; //-1 because otherwise leaf nodes end up with a height of 1 from the return call.

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        if(leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
    public int height() {
        return heightHelper(root);
    }

    public int minValue() {
        if(root == null) throw new NoSuchElementException("Tree is empty");

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public int maxValue() {
        if(root == null) throw new NoSuchElementException("Tree is empty");

        Node current = root;

        while(current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public boolean isBalanced() {
        return isBalancedHelper(root);
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

        List<Integer> leaves = levelOrderTraversal();

        for (Integer value : leaves) {
            System.out.print("Path from root '" + root.value + "' to '" + value + "': ");
            Node current = root;

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
                    System.out.print("Inorder Traversal: ");
                    bst.inorderTraversal();
                    System.out.println();

                    System.out.print("Preorder Traversal: ");
                    bst.preorderTraversal();
                    System.out.println();

                    System.out.print("Postorder Traversal: ");
                    bst.postorderTraversal();
                    System.out.println();

                    System.out.println("Level Order Traversal: " + bst.levelOrderTraversal());
                    break;

                case showProperties:
                    System.out.println("Height of BST: " + bst.height());
                    System.out.println("Minimum value in BST: " + bst.minValue());
                    System.out.println("Maximum value in BST: " + bst.maxValue());
                    System.out.println("Is BST Balanced? " + (bst.isBalanced() ? "Yes" : "No"));
                    break;

                case search:
                    int valueToSearch = promptUserForInteger("Enter value to search: ");
                    boolean found = bst.search(valueToSearch);
                    System.out.println("Value " + valueToSearch + " is " + (found ? "found" : "not found") + " in the BST.");
                    break;

                case delete:
                    int valueToDelete = promptUserForInteger("Enter value to delete: ");
                    bst.delete(valueToDelete);
                    System.out.println("Value " + valueToDelete + " has been deleted from the BST.");
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

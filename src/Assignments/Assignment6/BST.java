package Assignments.Assignment6;

import java.util.List;

public class BST {
    private class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

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
            //identify it and bypass the smallest descendant by linking it with its potential larger children.
            if (smallestDescendantPrev.left == smallestDescendant) {
                smallestDescendantPrev.left = smallestDescendant.right;
            } else {
                smallestDescendantPrev.right = smallestDescendant.right;
            }
        }
    }

    public void inorderTraversal() {
    }

    public void preorderTraversal() {
    }

    public void postorderTraversal() {
    }

    public List<Integer> levelOrderTraversal() {
        return null;
    }

    public int height() {
        return 0;
    }

    public int minValue() {
        return 0;
    }

    public int maxValue() {
        return 0;
    }

    public boolean isBalanced() {
        return false;
    }

    public void printPaths() {
    }

    public static void main(String[] args) {
    }
}

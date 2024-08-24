import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<E> extends MyAbstractList<E> { // <E> added here

    private class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    public MyLinkedList() {
        // Constructor logic (if any)
    }

    public MyLinkedList(E[] elements) {
        // Constructor with elements array
    }

    public void addFirst(E e) {
        head = new Node<>(e); // Use <E> for the Node type
        tail = head;
        size++;
    }

    public void addLast(E e) {
        // Add element at the end of the list
    }

    public E getFirst() {
        return null; // Return the first element
    }

    public E getLast() {
        return null; // Return the last element
    }

    public E removeFirst() {
        return null; // Remove and return the first element
    }

    public E removeLast() {
        return null; // Remove and return the last element
    }

    @Override
    public void add(int index, E e) { // Corrected method signature
        // Implementation for adding element at specified index
    }

    @Override
    public void clear() {
        // Clear the list
    }

    @Override
    public boolean contains(Object o) {
        return false; // Check if the list contains the element
    }

    @Override
    public E get(int index) {
        return null; // Get the element at the specified index
    }

    @Override
    public int indexOf(Object o) {
        return 0; // Get the index of the first occurrence of the element
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0; // Get the index of the last occurrence of the element
    }

    @Override
    public E remove(int index) {
        return null; // Remove the element at the specified index
    }

    @Override
    public E set(int index, E e) { // Corrected method signature
        return null; // Replace the element at the specified index and return the old element
    }

    @Override
    public Iterator<E> iterator() {
        return null; // Return an iterator over the elements in the list
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        super.forEach(action); // Perform the given action for each element of the list
    }

    @Override
    public Spliterator<E> spliterator() {
        return super.spliterator(); // Return a Spliterator over the elements in the list
    }
}

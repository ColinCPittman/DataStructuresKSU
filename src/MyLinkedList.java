import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<E> extends MyAbstractList<E> { // <E> added here

    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() { //Default constructor
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(E[] elements) {
        // Constructor with elements array
        for (E element : elements) {

        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode; // Use <E> for the Node type
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        // Add element at the end of the list
        if (head == null) {
            addFirst(e);
        }
        tail.next = new Node<>(e);
        size++;
    }

    public E getFirst() {
        if (head == null) {
            throw new UnderflowException("List is empty, cannot retrieve the first element.");
        }
        return head.data; // Return the first element
    }

    public E getLast() {
        if (head == null) { //since it is enforced that tail == null iff head == null,  I am checking for head == null here for logical consistency across the class.
            throw new UnderflowException("List is empty, cannot retrieve the last element.");
        }
        return tail.data; // Return the last element
    }

    public E removeFirst() {
        // Remove and return the first element
        if (head == null) {
            throw new UnderflowException("List is empty, cannot remove the first element.");
        }
        Node<E> deleted = head;
        head = head.next;
        size--;
        if (head == null) {
            tail = null;
        }
        return deleted.data;
    }

    public E removeLast() {
        // Return and remove the last element
        if (head == null) { //since it is enforced that tail == null iff head == null,  I am checking for head == null here for logical consistency across the class.
            throw new UnderflowException("List is empty, cannot remove the last element.");
        }

        if (head == tail) {
            Node<E> last = head;
            head = null;
            tail = null;
            size--;
            return head.data;
        }
        Node<E> previous = head;
        Node<E> current = head.next;
        while (current.next != null) {
            previous = previous.next;
            current = current.next;
        }
        tail = previous;
        tail.next = null;
        size--;
        return current.data;
    }

    @Override
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new OverflowException("Index out of bounds for list of size " + size + ".");
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        if (index == size) {
            addLast(e);
            return;
        }
        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        previous.next = new Node<E>(e, previous.next);
        size++;
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

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<E> extends MyAbstractList<E> implements Iterable<E> {
    private int modCount; // <E> added here

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
        modCount = 0;
        size = 0;
    }

    public MyLinkedList(E element) {
        addFirst(element);
    }

    public MyLinkedList(E[] elements) {
        // Constructor with elements array
        for (E element : elements) {
            addFirst(element);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
    public MyLinkedList copyList() {
        if(head == null) {
            return new MyLinkedList();
        }
        if(size == 1) {
            return new MyLinkedList(head.data);
        }
        MyLinkedList<E> newList = new MyLinkedList<>(head.data);
        Node<E> current = head.next;
        while(current != null) {
            newList.addLast(current.data);
            current = current.next;
        }
        return newList;
    }
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode; // Use <E> for the Node type
            tail = head;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        modCount++;
        size++;
    }

    public void addLast(E e) {
        // Add element at the end of the list
        if (head == null) {
            addFirst(e);
        }
        tail.next = new Node<>(e);
        tail = tail.next;
        modCount++;
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
        modCount++;
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
        modCount++;
        size--;
        return current.data;
    }

    @Override
    public void add(int index, E e) {
        if (index >= size || index < 0) {
            throw new OverflowException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }

        if (index == 0) {
            addFirst(e);
            return;
        }

        if (index == size - 1) {
            addLast(e);
            return;
        }

        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        previous.next = new Node<E>(e, previous.next);
        modCount++;
        size++;
    }

    @Override
    public void clear() {
        // Clear the list
        head = null;
        tail = null;
        modCount++;
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }

        return false;

    }

    @Override
    public E get(int index) {
        if (head == null) return null;

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }
        if (index == size - 1) {
            return tail.data;
        }
        if (index == 0) {
            return head.data;
        }
        Node<E> current = head.next;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        return current.data;
    }


    @Override
    public int indexOf(Object o) {
        // Get the index of the first occurrence of the element
        int index = 0;
        Node<E> current = head;

        while (current != null) {

            if (current.data.equals(o)) {
                return index;
            }

            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
       // Get the index of the last occurrence of the element
        int index = 0;
        int indexOfLastMatch = -1;
        Node<E> current = head;

        while (current != null) {

            if (current.data.equals(o)) {
                indexOfLastMatch = index;
            }

            current = current.next;
            index++;
        }
        return indexOfLastMatch;
    }

    @Override
    public E remove(int index) {
        // Remove the element at the specified index
        if(head == null) {
            throw new UnderflowException("Cannot remove from empty list.");
        }
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }
        if (index == 0) {
            return removeFirst();
        }
        if(index == size -1) {
            return removeLast();
        }

        Node<E> previous = head;
        Node<E> current = head.next;

        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
            current = current.next;
        }
        previous.next = current.next;
        size--;
        modCount++;
        return current.data;
    }

    @Override
    public E set(int index, E e) {
        // Replace the element at the specified index and return the old element
        if(head == null) {
            throw new IndexOutOfBoundsException("Cannot set elements on empty list.");
        }
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }
        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = e;
        modCount++;
        return oldData;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private int expectedModCount = modCount;
        private Node<E> lastReturned = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            // Handle removal when the last returned node is the head
            if (lastReturned == head) {
                removeFirst();
            } else {
                // Find the node before lastReturned
                Node<E> previous = head;
                while (previous.next != lastReturned) {
                    previous = previous.next;
                }
                previous.next = lastReturned.next;
                if (lastReturned == tail) { // If we're removing the tail
                    tail = previous;
                }
                size--;
                modCount++;
                expectedModCount++;
            }
            lastReturned = null;
        }
    }
    @Override
    public void forEach(Consumer<? super E> action) {
        Node<E> current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        return new LinkedListSpliterator(head, size, modCount); // Return a Spliterator over the elements in the list
    }
    private class LinkedListSpliterator implements Spliterator<E> {
        private Node<E> current;
        private int est; // size estimate
        private int expectedModCount;

        LinkedListSpliterator(Node<E> origin, int size, int modCount) {
            this.current = origin;
            this.est = size;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (current == null || modCount != expectedModCount) {
                return false;
            }
            action.accept(current.data);
            current = current.next;
            est--;
            return true;
        }

        @Override
        public Spliterator<E> trySplit() {
            // Not implementing splitting; return null means this Spliterator is not splittable
            return null;
        }

        @Override
        public long estimateSize() {
            return est;
        }

        @Override
        public int characteristics() {
            return SIZED | SUBSIZED | ORDERED | NONNULL;
        }
    }
}

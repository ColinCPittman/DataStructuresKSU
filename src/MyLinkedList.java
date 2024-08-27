import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<E> extends MyAbstractList<E> implements Iterable<E> {

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
    private int modCount;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        modCount = 0;
        size = 0;
    }

    public MyLinkedList(E element) {
        addFirst(element);
    }

    /**
     * Instantiates a new list containing the elements of the provided data array.
     *
     * @param dataArray Array of data elements to be instantiated.
     */
    public MyLinkedList(E[] dataArray) {
        for (E element : dataArray) {
            addLast(element);
        }
    }

    //BEGIN BONUS QUESTION
    //
    //
    //
    /** BONUS QUESTION
     * Copies the current list and returns the copy as a new MyLinkedList.
     *
     * @return a new MyLinkedList with the contents of the current list.
     */
    public MyLinkedList copyList() {
        if (head == null) {
            return new MyLinkedList();
        }
        if (size == 1) {
            return new MyLinkedList(head.data);
        }
        MyLinkedList<E> newList = new MyLinkedList<>(head.data);
        Node<E> current = head.next;
        while (current != null) {
            newList.addLast(current.data);
            current = current.next;
        }
        return newList;
    }
    //
    //
    //
    //END BONUS QUESTION

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


    /**
     * Adds the provided data element to the first position of the list.
     *
     * @param data Data element to be added.
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode; // Use <E> for the Node type
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        modCount++;
        size++;
    }

    /**
     * Adds the provided data element to the last position of the list.
     *
     * @param data Data element to be added.
     */
    public void addLast(E data) {
        // Add element at the end of the list
        if (head == null) {
            addFirst(data);
        }
        tail.next = new Node<>(data);
        tail = tail.next;
        modCount++;
        size++;
    }

    /**
     * Gets the data of the first element of the list.
     *
     * @return the data value of the first element of the list.
     */
    public E getFirst() {
        if (head == null) {
            throw new UnderflowException("List is empty, cannot retrieve the first element.");
        }

        return head.data; // Return the first element
    }

    /**
     * Gets the data of the last element of the list.
     *
     * @return the data value of the last element of the list.
     */
    public E getLast() {
        if (head == null) { //since it is enforced that tail == null iff head == null,  I am checking for head == null here for logical consistency across the class.
            throw new UnderflowException("List is empty, cannot retrieve the last element.");
        }

        return tail.data; // Return the last element
    }

    /**
     * Removes the first element of the list.
     *
     * @return the value of the removed element.
     */
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

    /**
     * Removed the last element of the list.
     *
     * @return the value of the removed element.
     */
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

    /**
     * Adds the provided data element to the provided index of the list.
     *
     * @param index position in the list to add the data element.
     * @param data  data element to be added at the index position.
     */
    @Override
    public void add(int index, E data) {
        if (index >= size || index < 0) {
            throw new OverflowException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size - 1) {
            addLast(data);
            return;
        }

        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        previous.next = new Node<E>(data, previous.next);
        modCount++;
        size++;
    }

    /**
     * Clears the list of all elements.
     */
    @Override
    public void clear() {
        // Clear the list
        head = null;
        tail = null;
        modCount++;
        size = 0;
    }

    /**
     * Searches the list for provided data element and returns boolean value indicating its presence in the list.
     *
     * @param data data element to be searched for.
     * @return true if data element is present in the list, otherwise false.
     */
    @Override
    public boolean contains(Object data) {
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }

        return false;

    }

    /**
     * Retrieves the data element at the provided index of the list.
     *
     * @param index index of the list to retrieve the data element.
     * @return the data element at the provided index of the list.
     */
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


    /**
     * Traverses the list from the head and returns the index of the first occurrence of
     * the provided element.
     *
     * @param data Data element to search list for.
     * @return the index of the first match or -1 if no match.
     */
    @Override
    public int indexOf(Object data) {
        int index = 0;
        Node<E> current = head;

        while (current != null) {

            if (current.data.equals(data)) {
                return index;
            }

            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Traverses the list from the head and returns the index of the last occurrence of
     * the provided element.
     *
     * @param data Data element to search list for.
     * @return the index of the last match of -1 if no match.
     */
    @Override
    public int lastIndexOf(Object data) {
        // Get the index of the last occurrence of the element
        int index = 0;
        int indexOfLastMatch = -1;
        Node<E> current = head;

        while (current != null) {

            if (current.data.equals(data)) {
                indexOfLastMatch = index;
            }

            current = current.next;
            index++;
        }
        return indexOfLastMatch;
    }

    /**
     * Removes the element of the list at the provided index.
     *
     * @param index index of the element to be removed from the list.
     * @return returns the removed data element.
     */
    @Override
    public E remove(int index) {
        if (head == null) {
            throw new UnderflowException("Cannot remove from empty list.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
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

    /**
     * Sets the data element at the position of index to the data value e.
     *
     * @param index index of the element to be set.
     * @param data  data element to set at the provided index.
     * @return
     */
    @Override
    public E set(int index, E data) {
        // Replace the element at the specified index and return the old element
        if (head == null) {
            throw new IndexOutOfBoundsException("Cannot set elements on empty list.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index of size " + index + " is not valid for zero-indexed list of size " + size + ".");
        }
        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = data;
        modCount++;
        return oldData;
    }


    //Below are generic implementations of iterators for convenience.


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

            if (lastReturned == head) {
                removeFirst();
            } else {
                Node<E> previous = head;
                while (previous.next != lastReturned) {
                    previous = previous.next;
                }
                previous.next = lastReturned.next;
                if (lastReturned == tail) {
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
        return new LinkedListSpliterator(head, size, modCount);
    }

    private class LinkedListSpliterator implements Spliterator<E> {
        private Node<E> current;
        private int est;
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
            return null; //Split not desired for linked list.
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

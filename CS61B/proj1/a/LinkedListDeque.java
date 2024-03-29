import java.util.LinkedList;

public class LinkedListDeque<T> {
    private class Node {
        private T data;
        private Node next, prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private int size;
    private Node sentinel = new Node(null); 

    public LinkedListDeque() {
        size = 0;
    }

    public LinkedListDeque(T item) {
        size = 0;
        this.addFirst(item);
    }


    /* Prepend item to the Linked List, if it's the first. It'll be the 
    sentinel node's next and previous node. */
    public void addFirst(T item) {
        Node first = new Node(item);
        size++;
        
        if (sentinel.next == null) {
            first.next = sentinel;
            first.prev = sentinel;
            sentinel.next = first;
            sentinel.prev = first;
        } else {
            first.next = sentinel.next;
            first.prev = sentinel;
            sentinel.next.prev = first;
            sentinel.next = first;
        }
    }

    /* Append item to the Linked List, if it's the first. It'll be the 
    sentinel node's next and previous node. */
    public void addLast(T item) {
        Node last = new Node(item);
        size++;

        if (sentinel.next == null) {
            last.next = sentinel;
            last.prev = sentinel;
            sentinel.next = last;
            sentinel.prev = last;
        } else {
            last.next = sentinel;
            last.prev = sentinel.prev;
            sentinel.prev.next = last;
            sentinel.prev = last;
        }
    }

    /* Return true if empty, false if the List contains any items. */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /* Returns the # of items in the Deque */
    public int size() {
        return this.size;
    }

    /* Print deque items. Each item will be seperated by a single space. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    /* Removes and returns the first item in the Linked List */
    public T removeFirst() {
        T item = sentinel.next.data;

        if (sentinel.next == null) {
            return null;
        } else {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return item;
        }
    }
 
    /* Removes and returns the last item in the Linked List */    
    public T removeLast() {
        T item = sentinel.prev.data;

        if (sentinel.prev == null) {
            return null;
        } else {
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return item;
        }
    }

    /* Get i'th item in the list */
    public T get(int index) {
        Node currentNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (currentNode.next == null) {
                return null;
            }
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /* get method that utilises recursion rather than iteration. */
    public T getRecursive(int index) {
        Node currentNode = sentinel.next;
        if (index > 0) {
            return getRecursive(index-1, currentNode.next);
        } else {
            return currentNode.data;
        }
    }    

    /* getRecursive overloaded method. Takes so as to not lose track of the already
    visited node. */
    public T getRecursive(int index, Node currentNode) {
        if (index > 0) {
            return getRecursive(index-1, currentNode.next);
        } else {
            return currentNode.data;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();

        for (int i = 100; i > 0; i--) {
            test.addFirst(i);
        }
        test.printDeque();
        System.out.println(test.size());
        System.out.println(test.getRecursive(25));
        System.out.println(test.get(25));
    }
}
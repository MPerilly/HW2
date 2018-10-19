import java.util.EmptyStackException;

public class StackList<E> {
    public class Node<E> {
        E element;
        Node<E> next;
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }
        public Node() {
            this(null, null);
        }
        //Accessor methods:
        public E getElement() {return this.element;}
        public Node<E> getNext() {return this.next;}
        //Modifiers:
        public void setElement(E newElem) {
            this.element = newElem;
        }
        public void setNext(Node<E> newNext) {
            this.next = newNext;
        }
    }
    private int size;
    private Node<E> top;
    //Accessor methods:
    public int getSize() {return this.size;}
    public boolean isEmpty() {
        if (this.top == null) {
            return true;
        }
        return false;
    }
    public E top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getElement();
    }
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E popped = this.top.getElement();
        this.top = this.top.getNext();
        size--;
        return popped;
    }
    //Modifiers:
    public void push(E newElem) {
        Node<E> newNode = new Node<E>(newElem, this.top);
        this.top = newNode;
        size++;
    }
}

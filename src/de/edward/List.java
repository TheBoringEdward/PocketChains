package de.edward;

import java.util.Comparator;

public class List<T> {

    private Node head; // What are all of these warning "Raw use of parameterized class 'Node'" about?
    private final Node tail;

    List(){
        head = new Node();
        tail = new Node();
        head.setNext(tail);
        tail.setPrev(head);
    }

    //Returns first Player
    public T get_first(){
        Node<T> n = head.getNext();
        return n.getContent();
    }

    //Returns last Node
    public Node getTail(){
        Node n = head;
        while( n.getNext() != null ) {
            n = n.getNext();
        }
        return n;
    }

    //Returns last Player
    public T get_last(){
        Node<T> n = getTail();
        return n.getContent();
    }

    private void exchange(Node<T> m, Node<T> n){
        Node<T> n2 = new Node<T>();
        n2.setContent(n.getContent());
        n.setContent(m.getContent());
        m.setContent(n2.getContent());
    }

    public void sort(Comparator comp){
        // bubble sort
        Node<T> m = new Node<T>();
        Node<T> n = new Node<T>();
        m.setContent(get_first());
        n.setPrev(m);
        m.setNext(n);

        while(m.getNext() != null){
            while(n.getNext() != null){
                T p = n.getContent();
                T q = m.getContent();
                if (comp.compare(p,q) <0 ){
                    exchange(m,n);
                }
                n = n.getNext();
                m = m.getNext();
            }
        }
    }

    //Sets next Player of current Player
    public void append(T p){
        Node n = tail.getPrev();
        Node n2 = new Node(p);
        n2.setPrev(n);
        n2.setNext(tail);
        n.setNext(n2);
        tail.setPrev(n2);
        System.out.println("This object has been appended:  " + get_last() + "\n");
    }

    //Prints out the entire List of Nodes/Players
    public void print(){
        Node n = head;
        while(n.getNext() != null){
            System.out.println("\n");
            n.print();
            n = n.getNext();
        }
    }

    //Builds String from the entire List of Nodes/Player
    public String toString() {
        StringBuilder a = new StringBuilder();
        Node n = head;
        if (n.getNext() != null){
            n = n.getNext();
        }
        while(n.getNext() != null){
            a.append(n.toString());
            n = n.getNext();
        }
        a.append(n.toString());
        return a.toString();
    }

    //Remove selected Node/Player
    public void remove(Player p) {
        Node n = head; //Current Player to be deleted
        Node pre_n = head; //Player/Node prior to n
        //Check if player even exists
        if (n.getContent() == p) {
            head = n.getNext();
        } else {
            n = n.getNext();
            while (n.getContent() != p && n.getNext() != null) {
                n = n.getNext();
                pre_n = pre_n.getNext();
            }
            if (n.getNext() != null) {
                pre_n.setNext(n.getNext());
                n.setContent(null);
            }
            //Else do nothing
        }
    }

    public void insert_after( Player p, Player predecessor ) {
        Node n = head;
        Node newPlayer = new Node(p);

        if (n.getContent() == predecessor) {
            newPlayer.setNext(n.getNext());
            n.setNext(newPlayer);
        } else {
            while (n.getContent() != predecessor && n.getNext() != null){
                n = n.getNext();
            }
            if (n.getNext() != null){
                newPlayer.setNext(n.getNext());
                n.setNext(newPlayer);
            }
            //Else do nothing
        }
    }

    public Boolean isEmpty(){
        return head == null;
    }

}

package de.edward;

public class List<T> {

    private Node head;
    private Node tail;

    List(){
        head = new Node();
        tail = new Node();
        head.setNext(tail);
        tail.setPrev(head); //Always points to the head...
    }

    //Returns first Player
    public T get_first(){
        Node<T> n = head.getNext();
        return n.getContent();
    }

    //Returns last Node
    public Node get_tail(){
        Node n = head;
        while( n.getNext() != null ) {
            n = n.getNext();
        }
        return n;
    }

    //Returns last Player
    public T get_last(){
        Node<T> n = get_tail();
        return n.getContent();
    }

    //Sets next Player of current Player
    public void append(T p){
        Node n = get_tail();
        Node n2 = new Node(p);
        n.setNext(n2);
        tail.setPrev(n2);
        System.out.println("Player has been appended:  " + get_last());
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

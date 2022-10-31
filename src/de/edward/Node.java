package de.edward;

public class Node<T> {

    private Node next;

    private Node prev;

    private T content;

    Node(T p){
        content = p;
        next = null;
        prev = null;
    }

    Node(){
        content = null;
        next = null;
        prev = null;
    }

    //Returns next Player of current Player
    public Node getNext(){
        return next;
    }

    //Sets next Player of current Player
    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    //Returns Player Content
    public T getContent() {
        return content;
    }

    //Sets Player Content
    public void setContent(T p) {
        this.content = p;
    }

    //Prints Player Content
    public void print(){
        if( content != null){
            System.out.println(content.toString());
        } else {
            System.out.println("\n null");
        }
    }

    //Builds String from Player Content
    public String toString(){
        if( content==null ){
            return "null";
        }else {
            return content.toString();
        }
    }

}

package de.edward;

public class Character {

    private String name;

    private String clas; // Can't name it 'class'.

    private int hlth;

    Character(String name, String clas, int hlth){
        this.name = name;
        this.clas = clas;
        this.hlth = hlth;
    }

    public void print(){
        System.out.println("\n name = " + name);
        System.out.println("\n class = " + clas);
        System.out.println("\n health = " + hlth);
        System.out.println("\n Next...");
    }

    public String toString(){
        return name + "\n" + clas + "\n" + hlth + "\n\n";
    }

    //Sets Name
    public void setName(String name) {
        this.name = name;
    }
    //Returns Name
    public String getName(){
        return name;
    }

    public void setClas(String clas){
        this.clas = clas;
    }

    public String getClas(){
        return clas;
    }

    public void setHlth(int hlth){
        this.hlth = hlth;
    }

    public int getHlth(){
        return hlth;
    }

}

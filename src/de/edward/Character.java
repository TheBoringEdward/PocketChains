package de.edward;

public class Character {

    private String name;
    private String clas; // Can't name it 'class'.
    private String subclas; // Subclass
    private int hlth; // Base Health

    Character(String name, String clas, String subclas, int hlth){
        this.name = name;
        this.clas = clas;
        this.subclas = subclas;
        this.hlth = hlth;
    }

    public void print(){
        System.out.println("\n name = " + name);
        System.out.println("\n class = " + clas);
        System.out.println("\n subclass = " + subclas);
        System.out.println("\n health = " + hlth);
        System.out.println("\n Next...");
    }

    public String toString(){
        return name + "\n" + clas + "\n" + subclas + "\n" + hlth + "\n\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setClas(String clas){
        this.clas = clas;
    }

    public String getClas(){
        return clas;
    }

    public void setSubclas(String subclas){
        this.subclas = subclas;
    }

    public String getSubclas(){
        return subclas;
    }

    public void setHlth(int hlth){
        this.hlth = hlth;
    }

    public int getHlth(){
        return hlth;
    }

}

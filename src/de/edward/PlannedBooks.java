package de.edward;

public class PlannedBooks{

    private String name; // Name of the title
    private String desc; // Description of the title
    private int hlth; // Probability to finish work

    PlannedBooks(String name, String desc, int hlth){
        this.name = name;
        this.desc = desc;
        this.hlth = hlth;
    }

    public void print(){
        System.out.println("\n name = " + name);
        System.out.println("\n class = " + desc);
        System.out.println("\n probability = " + hlth);
        System.out.println("\n Next...");
    }

    public String toString(){
        return name + "\n" + desc + "\n" + hlth + "\n\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public void setHlth(int hlth){
        this.hlth = hlth;
    }

    public int getHlth(){
        return hlth;
    }

}

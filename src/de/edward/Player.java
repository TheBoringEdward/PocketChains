package de.edward;

public class Player {

    private String name;
    private double value;
    private String team;

    Player(String name, double value, String team){
        this.name = name;
        this.value = value;
        this.team = team;
    }

    //Prints out Player information
    public void print(){
        System.out.println("\n name = " + name);
        System.out.println("\n value = " + value);
        System.out.println("\n team = " + team);
        System.out.println("\n Next...");
    }

    //Builds String from Player information
    public String toString(){
        return name + "\n" + value + "\n" + team + "\n\n";
    }

    //Sets Player Name
    public void setName(String name) {
        this.name = name;
    }
    //Returns Player Name
    public String getName() {
        return name;
    }

    //Sets Player Value
    public void setValue(double value) {
        this.value = value;
    }
    //Returns Player Value
    public double getValue() {
        return value;
    }

    //Sets Player Team
    public void setTeam(String team) {
        this.team = team;
    }
    //Returns Player Team
    public String getTeam() {
        return team;
    }

}

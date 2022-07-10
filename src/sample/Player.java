package sample;

import java.io.Serializable;

public class Player implements Serializable {
    String name;
    double points;

    public Player(String name, double points){
        this.name=name;
        this.points=points;
    }
    public String toString(){
       return name +"  " +points;
    }
    public double getPoints(){
        return points;
    }
}

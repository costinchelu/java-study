package sec9a_interfaces.challenge;

import java.util.ArrayList;
import java.util.List;

public class Player implements iSaveable {
    private String name;
    private int hitPoints;
    private int strength;
    private String weapon;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "sword";
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getHitPoints() { return hitPoints; }

    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

    public int getStrength() { return strength; }

    public void setStrength(int strength) { this.strength = strength; }

    public String getWeapon() { return weapon; }

    public void setWeapon(String weapon) { this.weapon = weapon; }

    @Override
    public String toString() {
        return "Player " + this.name + " \' " + " hitpoints = " + this.hitPoints + ", strength = " + strength +
                 ", weapon = " + weapon + " \'";
    }

    //in a game, this method will save a character(player) status
    @Override
    public List<String> write() {
        List<String> values = new ArrayList<String>();
        values.add(0, this.name);                           //already a string, no parsing needed
        values.add(1, "" + this.hitPoints);         //parse int to String
        values.add(2, "" + this.strength);
        values.add(3, this.weapon);
        return values;
    }

    //this would read a player's status for loading up saved data
    @Override
    public void read(List<String> savedValues) {
        if(savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));      //parse a String savedValues.get(1) to int
            this.strength = Integer.parseInt(savedValues.get(2));
            this.weapon = savedValues.get(3);
        }
    }
}

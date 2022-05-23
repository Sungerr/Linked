package com.jonesclass.sung;

public class ObjectStats {

    private  int health = -1;
    private int damage = -1;
    private String type = "";

    public ObjectStats(String type) {
        this.type = type;

        switch (type) {
            case "Planet":
                health = 3;
                damage = 1;
                break;
            case "Asteroid":
                health = 1;
                damage = 1;
                break;
            case "Satellite":
                damage = 1;
                break;
        }
    }

    public void setHealth(int value) {
        health += value;
    }

    public int getHealth() {return health;}
    public int getDamage() {return damage;}
    public String getType() {return type;}

}

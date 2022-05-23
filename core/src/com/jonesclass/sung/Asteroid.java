package com.jonesclass.sung;

import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    int vertexTotal;
    private boolean isDestroyed;

    private float x, y;
    private int width, height, type, health;
    private double  rotationSpeed, radians, dx, dy;
    private Vector2[] shape;
    private float[] dists;



    public Asteroid(float x, float y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;

        switch (type) {
            case SMALL:
                vertexTotal = 8;
                width = 5;
                height = 5;
                health = 1;
                break;
            case MEDIUM:
                vertexTotal = 10;
                width = 7;
                height = 7;
                health = 2;
                break;
            case LARGE:
                vertexTotal = 12;
                width = 9;
                height = 9;
                health = 3;
                break;
        }

        radians = Math.random() * (2 * Math.PI);
        shape = new Vector2[vertexTotal];
        dists = new float[vertexTotal];

        int radius = width / 2;
        for (int i = 0; i < vertexTotal; i++) {
            dists[i] =(float) (Math.random() * radius/2) + radius/2;
        }

        setShape();
    }


    private void setShape() {
        float angle = 0;
        for (int i = 0; i < vertexTotal; i++) {
            shape[i] = new Vector2();

            //Makes square shape for some reason
            shape[i].x = x;
            shape[i].y = y;

            //Did not have enough time to fix random asteroid shapes
//            shape[i].x = (float) (x + Math.cos(angle + radians) * dists[i]);
//            shape[i].y = (float) (x + Math.sin(angle + radians) * dists[i]);
            angle += (float) ((2 * Math.PI) / vertexTotal);
        }
    }

    public int getType() { return type; }
    public Vector2[] getPoints() { return shape; }
    public float getX() {return x;}
    public float getY() {return y;}
    public int getHealth() {return health;}

}

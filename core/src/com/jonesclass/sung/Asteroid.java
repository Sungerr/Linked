package com.jonesclass.sung;

import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    int vertexTotal;
    private boolean isDestroyed;

    private float x, y;
    private int width, height, type;
    private double speed, rotationSpeed, radians, dx, dy;
    private Vector2[] shape;
    private float[] dists;



    public Asteroid(float x, float y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;

        switch (type) {
            case SMALL:
                vertexTotal = 8;
                width = 6;
                height = 6;
                speed = (float) (Math.random() * 30) + 70;
                break;
            case MEDIUM:
                vertexTotal = 10;
                width = 10;
                height = 10;
                speed = (float) (Math.random() * 10) + 50;
                break;
            case LARGE:
                vertexTotal = 12;
                width = 20;
                height = 20;
                speed = (float) (Math.random() * 10) + 20;
                break;
        }

        rotationSpeed = (Math.random() * 2) - 1;
        radians = Math.random() * (2 * Math.PI);
        dx = Math.cos(radians) * speed;
        dy =  Math.sin(radians) * speed;

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
            shape[i].x = (float) (x + Math.cos(angle + radians) * dists[i]);
            shape[i].y = (float) (x + Math.sin(angle + radians) * dists[i]);
            angle += (float) ((2 * Math.PI) / vertexTotal);
        }
    }

    public int getType() { return type; }
    public Vector2[] getPoints() { return shape; }
    public float getX() {return x;}
    public float getY() {return y;}

}

package com.jonesclass.sung;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;

public class B2dModel {

    public World world;
    private MouseJoint mouseJoint = null;
    public Body circle1;
    public Body circle2;
    public Body connectBody;


    public B2dModel () {
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new B2dContactListener(this));
        createFloor();


        // get our body factor and store it in bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        // add a new rubber ball at position 1, 1
        //small
        circle2 = bodyFactory.makeCirclePolyBody(2, 1, 2, BodyFactory.SATELLITE, BodyType.DynamicBody,true);


        //Big circle
        circle1 = bodyFactory.makeCirclePolyBody(5, 1, 5, BodyFactory.PLANET, BodyType.DynamicBody,false);

    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }



    private void createFloor() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-10);
        Body bodys = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(500,10);
        bodys.createFixture(shape, 0.0f);
        shape.dispose();

    }


    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        Body bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }

}

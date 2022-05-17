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

import java.util.ArrayList;

public class B2dModel {

    public World world;
    private MouseJoint mouseJoint = null;
    public Body circle1;
    public Body circle2;
    public Body asteroid;
    public BodyFactory bodyFactory;

    public B2dModel () {
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new B2dContactListener(this));
        bodyFactory = BodyFactory.getInstance(world);

        //Small
        circle2 = bodyFactory.makeCirclePolyBody(2, 1, 2, BodyFactory.SATELLITE, BodyType.DynamicBody,true);

        //Big circle
        circle1 = bodyFactory.makeCirclePolyBody(5, 1, 5, BodyFactory.PLANET, BodyType.DynamicBody,false);

        asteroid = bodyFactory.createAsteroid(new Asteroid(3,3, Asteroid.SMALL));

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

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);

        Body bodyk = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        bodyk.createFixture(shape, 0.0f);

        shape.dispose();
        bodyk.setLinearVelocity(0, 0.75f);
    }

}

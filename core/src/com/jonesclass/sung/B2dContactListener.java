package com.jonesclass.sung;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class B2dContactListener implements ContactListener {

    private B2dModel parent;

    public B2dContactListener(B2dModel parent) {
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();


        ObjectStats data1 = (ObjectStats) fa.getBody().getUserData();
        ObjectStats data2 = (ObjectStats) fb.getBody().getUserData();

        System.out.println(data1.getType() + " " + data2.getType());
        if (data1.getType().equals("Planet") && data2.getType().equals("Asteroid")) {
            data1.setHealth(data2.getDamage() * -1);
        } else if (data2.getType().equals("Planet") && data1.getType().equals("Asteroid")) {
            data2.setHealth(data1.getDamage() * -1);
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

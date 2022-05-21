package com.jonesclass.sung.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonesclass.sung.B2dModel;
import com.jonesclass.sung.BodyFactory;
import com.jonesclass.sung.Main;
import com.jonesclass.sung.Utilities;

import java.security.Key;

import sun.nio.ch.Util;

public class GameScreen  implements Screen  {


    //TODO: enemies, obstacles, highscore, weapon speed slider

    final Main game;
    B2dModel model;
    OrthographicCamera cam;
    Box2DDebugRenderer debugRenderer;
    private MouseJointDef mouseJointDef;
    private MouseJoint mouseJoint;
    private RevoluteJointDef revoluteJointDef;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private Touchpad touchpad;
    private Stage stage;
    private final int WIDTH, HEIGHT;

    public GameScreen(final Main game) {
        this.game = game;
        WIDTH = Gdx.graphics.getWidth() / 32;
        HEIGHT = Gdx.graphics.getHeight() / 32;
        cam = new OrthographicCamera(WIDTH, HEIGHT);


        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        model = new B2dModel();
    }

    @Override
    public void show() {

        Table table = new Table();
        stage = new Stage();

        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        touchpad = new Touchpad(0, Utilities.touchpadStyle());
        touchpad.setColor( 255,255,255,0.55f);

        table.setFillParent(true);
        table.bottom().left();
        table.add(touchpad).width(200).height(200).pad(70);
        stage.addActor(table);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();

                model.circle1.setLinearVelocity(15 * deltaX, 15 * deltaY);


            }
        });

        inputMultiplexer.addProcessor(stage);

        revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.bodyA = model.circle1;
        revoluteJointDef.bodyB = model.circle2;
        revoluteJointDef.localAnchorA.set(new Vector2(5, 0));
        revoluteJointDef.localAnchorB.set(new Vector2(0, 0));
        revoluteJointDef.enableMotor = true;
        revoluteJointDef.motorSpeed = 100f;
        revoluteJointDef.maxMotorTorque = 100;
        revoluteJointDef.referenceAngle = 0;
        model.world.createJoint(revoluteJointDef);
        table.debug();

        model.spawnAsteroids();

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);
        stage.act(delta);
        stage.draw();
        model.logicStep(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setScreen(game.getScreen("Menu"));
        }

        for (Body b : BodyFactory.getGameBodies()) {
            if (b.getPosition().x > WIDTH/2) { b.setTransform((WIDTH/2 * -1),b.getPosition().y,b.getAngle());}
            if (b.getPosition().x < (WIDTH/2 * -1)) { b.setTransform((WIDTH/2),b.getPosition().y,b.getAngle());}

            if (b.getPosition().y > HEIGHT/2) { b.setTransform(b.getPosition().x,(HEIGHT/2 * -1),b.getAngle());}
            if (b.getPosition().y < (HEIGHT/2 * -1)) { b.setTransform(b.getPosition().y,HEIGHT/2,b.getAngle());}
        }

        System.out.println(model.circle1.getPosition().x + " " + model.circle1.getPosition().y);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}

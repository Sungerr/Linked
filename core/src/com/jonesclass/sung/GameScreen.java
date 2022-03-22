package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class GameScreen extends InputAdapter implements Screen  {

    final Main game;
    B2dModel model;
    OrthographicCamera cam;
    Box2DDebugRenderer debugRenderer;
    private MouseJointDef mouseJointDef;

    public GameScreen(final Main game) {
        this.game = game;
        cam = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        model = new B2dModel();
    }

    @Override
    public void show() {
        mouseJointDef = new MouseJointDef();
        mouseJointDef.bodyA = model.world.createBody(new BodyDef());
        mouseJointDef.collideConnected = true;
        mouseJointDef.maxForce = 500;
    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);
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

    private Vector3 tmp = new Vector3();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cam.unproject(tmp.set(screenX, screenY, 0));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }
}

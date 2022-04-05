package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class GameScreen extends InputAdapter implements Screen  {

    final Main game;
    B2dModel model;
    OrthographicCamera cam;
    Box2DDebugRenderer debugRenderer;
    private MouseJointDef mouseJointDef;
    private MouseJoint mouseJoint;
    private DistanceJointDef distanceJointDef;

    public GameScreen(final Main game) {
        this.game = game;
        cam = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        model = new B2dModel();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        mouseJointDef = new MouseJointDef();
        mouseJointDef.bodyA = model.world.createBody(new BodyDef());
        mouseJointDef.collideConnected = true;

        distanceJointDef = new DistanceJointDef();
        distanceJointDef.bodyA = model.circle1;
        distanceJointDef.bodyB = model.connectBody;
        distanceJointDef.length = 5f;



        model.world.createJoint(distanceJointDef);

        //change this to edit speed
        mouseJointDef.maxForce = 1000;
    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);

        model.circle2.applyAngularImpulse(10,true);

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
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        @Override
        public boolean reportFixture(Fixture fixture) {
            if (!fixture.testPoint(tmp.x, tmp.y)) {
                return false;
            } else if (!fixture.testPoint(model.circle2.getPosition())) {
                return false;
            }
            mouseJointDef.bodyB = fixture.getBody();
            mouseJointDef.target.set(tmp.x, tmp.y);
            mouseJoint = (MouseJoint) model.world.createJoint(mouseJointDef);

            return false;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cam.unproject(tmp.set(screenX, screenY, 0));
        model.world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (mouseJoint == null)
            return false;


        model.world.destroyJoint(mouseJoint);
        mouseJoint = null;


        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (mouseJoint == null)
            return false;

        cam.unproject(tmp.set(screenX, screenY, 0));
        mouseJoint.setTarget(tmp2.set(tmp.x, tmp.y));
        mouseJoint.setDampingRatio(100f);
        mouseJoint.setFrequency(50f);

        return true;
    }
}

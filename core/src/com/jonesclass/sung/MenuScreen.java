package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jonesclass.sung.Utilities;



public class MenuScreen implements Screen {

    private Main game;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private BitmapFont font;
    private TextButton startButton;
    private Label titleLabel;

    public MenuScreen(final Main game) {
        this.game = game;

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        table = new Table();

        try {
            titleLabel = new Label("Linked", Utilities.labelStyle());
            titleLabel.setFontScale(10f);
            startButton = new TextButton("Start", Utilities.buttonStyles("default-rect", "default-rect-down"));
            startButton = Utilities.buttonSettings(startButton);
            table.center().setFillParent(true);
            table.add(titleLabel);
            table.row();
            table.add(startButton).center().width(300).height(80);
            stage.addActor(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.debug();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        stage.act(delta);
        stage.draw();

        if (startButton.isPressed()) {
            game.setScreen(new GameScreen(game));
        }
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
        stage.dispose();
    }


}
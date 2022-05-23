package com.jonesclass.sung.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jonesclass.sung.Main;
import com.jonesclass.sung.ScoreManager;
import com.jonesclass.sung.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScoreScreen extends InputAdapter implements Screen {

    private Main game;
    private OrthographicCamera cam;
    private Label failLabel, nameLabel, scoreLabel, dateLabel;
    private Stage stage;
    private Table table;

    public HighScoreScreen(final Main game) {
        this.game = game;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);


        stage = new Stage();
        table = new Table();
//        table.debug();

        try {
            String failText = "Sorry Mr.Jones but I was unable to get the JDBC\nfor sqlite working on Android :(\n" +
                    "\nSee details in the SqliteManager class";
            failLabel = new Label(failText, Utilities.labelStyle(8));
            nameLabel = new Label(ScoreManager.getName(), Utilities.labelStyle(10));
            scoreLabel = new Label(ScoreManager.getScore(), Utilities.labelStyle(10));
            dateLabel = new Label(ScoreManager.getDate(), Utilities.labelStyle(10));


            table.pad(200).top().center().setFillParent(true);
            table.add(failLabel).padBottom(300).center().top();
            table.row();
            table.add(nameLabel).center().padBottom(100);
            table.row();
            table.add(scoreLabel).padBottom(100);
            table.row();
            table.add(dateLabel);


            stage.addActor(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        stage.act(delta);
        stage.draw();


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(game.getScreen("Menu"));
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

    }
}

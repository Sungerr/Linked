package com.jonesclass.sung.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jonesclass.sung.Main;
import com.jonesclass.sung.ScoreManager;
import com.jonesclass.sung.Utilities;
import com.jonesclass.sung.screens.GameScreen;


public class MenuScreen implements Screen {


    private Main game;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private BitmapFont font;
    private TextButton startButton, scoresButton, exitButton;
    private Label titleLabel;

    public MenuScreen(final Main game) {
        this.game = game;

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        table = new Table();

        try {
            titleLabel = new Label("Linked", Utilities.labelStyle(12));

            startButton = new TextButton("Start", Utilities.buttonStyles());
            scoresButton = new TextButton("High Scores", Utilities.buttonStyles());
            exitButton = new TextButton("Exit", Utilities.buttonStyles());


            table.center().top().setFillParent(true);
            table.add(titleLabel).padBottom(110).padTop(80);
            table.row();
            table.add(startButton).padBottom(90).width(550).height(130);
            table.row();
            table.add(scoresButton).padBottom(90).width(550).height(130);
            table.row();
            table.add(exitButton).width(550).height(130);

            stage.addActor(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        table.debug();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        game.getAudioManager().playMenu();
        startButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getAudioManager().playButton();
                ScoreManager.setScore(0);
                game.getAudioManager().stopAll();
                game.setScreen(game.getScreen("Game"));
            }
        });

        scoresButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.getAudioManager().playButton();
                game.setScreen(game.getScreen("HighScore"));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        stage.act(delta);
        stage.draw();

        if (exitButton.isPressed()) {
            Gdx.app.exit();
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}

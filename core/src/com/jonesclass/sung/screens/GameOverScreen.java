package com.jonesclass.sung.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jonesclass.sung.Main;
import com.jonesclass.sung.SqliteManager;
import com.jonesclass.sung.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameOverScreen extends InputAdapter implements Screen {
    private Main game;
    private OrthographicCamera cam;
    private int score = 0;
    private String dateString = "";
    private String name = "";
    private SqliteManager sql;
    private Stage stage;
    private Table table;
    private Label titleLabel;
    private TextField textField;
    private TextButton menuButton, scoresButton, submitButton;

    public GameOverScreen(final Main game) {
        this.game = game;
        sql = new SqliteManager();

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        table = new Table();
        table.debug();

        try {
            titleLabel = new Label("Enter Your Name", Utilities.labelStyle(10));
            textField = new TextField("", Utilities.textFieldStyle());
            menuButton = new TextButton("Menu", Utilities.buttonStyles());
            scoresButton = new TextButton("High Scores", Utilities.buttonStyles());
            submitButton = new TextButton("Submit", Utilities.buttonStyles());

            table.top().setFillParent(true);
            table.add(titleLabel).padBottom(300).padLeft(1200).padTop(50).center().top();
            table.row();
            table.add(textField).center().padLeft(450).padBottom(100).height(130).width(1000);
            table.add(submitButton).height(130).width(550).padBottom(100).padRight(500);
            table.row();
            table.add(menuButton).height(130).width(550);
            table.add(scoresButton).height(130).width(550).padRight(500);

            stage.addActor(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        dateString = formatter.format(date);

        menuButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getScreen("Menu"));
            }
        });

        scoresButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getScreen("HighScore"));
            }
        });

        submitButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //Unfortunately unable to get score done in time :(
                sql.insertScore(textField.getText(), 0, dateString);
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

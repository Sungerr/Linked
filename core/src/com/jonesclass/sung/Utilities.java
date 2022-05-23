package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Align;

public class Utilities {

    private static TextButtonStyle textButtonStyle;
    private static TextButton textButton;
    private static Touchpad.TouchpadStyle touchpadStyle;
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private static TextureAtlas textureAtlasTest = new TextureAtlas("uiskin.atlas");
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
    private static FreeTypeFontParameter parameter = new FreeTypeFontParameter();


    public static TextButton buttonSettings(TextButton button) {
        button.getLabel().setAlignment(Align.center);
        button.getLabel().setFontScale(5f);

        return button;
    }

    public static TextButtonStyle buttonStyles() {
        parameter.size = 85;
        BitmapFont font = generator.generateFont(parameter);

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.YELLOW;
        textButtonStyle.up = skin.getDrawable("default-rect-down");
        textButtonStyle.over = skin.getDrawable("default-rect-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        return textButtonStyle;
    }

    public static Label.LabelStyle labelStyle(int fontSize) {

        parameter.size = fontSize * 10;

        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        return labelStyle;
    }

    public static Touchpad.TouchpadStyle touchpadStyle() {
        skin.addRegions(textureAtlasTest);
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = skin.getDrawable("default-pane");
        touchpadStyle.knob = skin.getDrawable("default-round-large");
        touchpadStyle.background.setMinHeight(200);
        touchpadStyle.knob.setMinHeight(60);
        touchpadStyle.background.setMinWidth(200);
        touchpadStyle.knob.setMinWidth(60);

        return touchpadStyle;
    }

    public static TextField.TextFieldStyle textFieldStyle() {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        BitmapFont font = generator.generateFont(parameter);
        style.font = font;
        style.fontColor = Color.WHITE;
        style.background = skin.getDrawable("textfield");
        style.background.setMinWidth(800);
        return style;
    }

    public static Skin skin() {
        return skin;
    }
}

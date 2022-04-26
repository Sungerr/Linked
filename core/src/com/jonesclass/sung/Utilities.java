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
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Align;

public class Utilities {

    private static TextButtonStyle textButtonStyle;
    private static TextButton textButton;
    private static Touchpad.TouchpadStyle touchpadStyle;
    private static Skin skin;
    private static TextureAtlas textureAtlasTest;
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
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        textureAtlasTest = new TextureAtlas("uiskin.atlas");
        skin.addRegions(textureAtlasTest);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.YELLOW;
        textButtonStyle.up = skin.getDrawable("default-rect");
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
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = skin.getDrawable("default-rect-pad");
        touchpadStyle.knob = skin.getDrawable("default-round");
        return touchpadStyle;
    }
}

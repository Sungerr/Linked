package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import sun.font.TrueTypeFont;

public class Utilities {

    private static TextButtonStyle textButtonStyle;
    private static TextButton textButton;
    private static Skin buttonSkin;
    private static TextureAtlas textureAtlasTest;


    public static TextButton buttonSettings(TextButton button) {
        button.getLabel().setAlignment(Align.center);
        button.getLabel().setFontScale(5f);

        return button;
    }

    public static TextButtonStyle buttonStyles() {
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin(Gdx.files.internal("uiskin.json"));
        textureAtlasTest = new TextureAtlas("uiskin.atlas");
        buttonSkin.addRegions(textureAtlasTest);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.YELLOW;
        textButtonStyle.up = buttonSkin.getDrawable("default-rect");
        textButtonStyle.over = buttonSkin.getDrawable("default-rect-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        return textButtonStyle;
    }

    public static Label.LabelStyle labelStyle() {

        BitmapFont font = new BitmapFont();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        return labelStyle;
    }
}

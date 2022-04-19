package com.jonesclass.sung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;

public class Utilities {

    private static TextButtonStyle textButtonStyle;
    private static TextButton textButton;
    private static BitmapFont font;
    private static Skin buttonSkin;
    private static TextureAtlas textureAtlasTest;

    public static TextButton buttonSettings(TextButton button) {
        button.getLabel().setAlignment(Align.center);
        button.getLabel().setFontScale(3f,3f);
        return button;
    }

    public static TextButtonStyle buttonStyles(String upStyle, String overStyle) {
        font = new BitmapFont();
        buttonSkin = new Skin(Gdx.files.internal("uiskin.json"));
        textureAtlasTest = new TextureAtlas("uiskin.atlas");
        buttonSkin.addRegions(textureAtlasTest);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.YELLOW;
        textButtonStyle.up = buttonSkin.getDrawable(upStyle);
        textButtonStyle.over = buttonSkin.getDrawable(overStyle);
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        return textButtonStyle;
    }
}

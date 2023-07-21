package com.avs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import currentpackage.CurrentPackage;

public abstract class BaseGame extends Game {

    private final String PAC_CAT = CurrentPackage.getCurrentPackage(this.getClass()) + ".";
    private static BaseGame game;
    public static Label.LabelStyle labelStyle;
    public static TextButton.TextButtonStyle textButtonStyle;

    public BaseGame() {
        game = this;
    }

    @Override
    public void create() {
        InputMultiplexer iM = new InputMultiplexer();
        Gdx.input.setInputProcessor(iM);
        FreeTypeFontGenerator fontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal("resources/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = Texture.TextureFilter.Linear;
        fontParameters.magFilter = Texture.TextureFilter.Linear;
        BitmapFont customFont = fontGenerator.generateFont(fontParameters);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = customFont;
        textButtonStyle = new TextButton.TextButtonStyle();
        Texture buttonTex = new Texture("resources/button.png");
        NinePatch buttonPatch = new NinePatch(buttonTex, 24, 24, 24, 24);
        textButtonStyle.up = new NinePatchDrawable(buttonPatch);
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.GRAY;
    }

    public static void setActiveScreen(BaseScreen baseScreen) {
        game.setScreen(baseScreen);
    }

}

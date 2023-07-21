package com.avs.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Wall extends BaseActor {

    public Wall(float x, float y, float width, float height, Stage s) {
        super(x, y, s);
        loadTexture("resources/white-square.png");
        setSize(width, height);
        setBoundaryRectangle();
        setColor(Color.DARK_GRAY);
    }

}

package com.avs.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Paddle extends BaseActor {

    public Paddle(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("resources/paddle.png");
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float positionX = this.getX();
            this.setX(positionX - 15);
            this.boundToWorld();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float positionY = this.getX();
            this.setX(positionY - -15);
            this.boundToWorld();
        }
    }

}

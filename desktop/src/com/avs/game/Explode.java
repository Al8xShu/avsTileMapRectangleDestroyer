package com.avs.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Explode extends BaseActor {

    public Explode(float x, float y, Stage s) {
        super(x, y, s);
        loadAnimationFromSheet("resources/explosion.png", 6, 6, 0.03f, false);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if (isAnimationFinished()) {
            remove();
        }
    }
}

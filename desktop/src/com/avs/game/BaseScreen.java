package com.avs.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;

    public BaseScreen() {
        mainStage = new Stage();
        uiStage = new Stage();
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);
        initialize();
    }

    public abstract void initialize();

    public abstract void update(float dt);

    @Override
    public void render(float dt) {
        uiStage.act(dt);
        mainStage.act(dt);
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
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
    public void dispose() {

    }

    @Override
    public void show() {
        InputMultiplexer iM = (InputMultiplexer) Gdx.input.getInputProcessor();
        iM.addProcessor(this);
        iM.addProcessor(uiStage);
        iM.addProcessor(mainStage);
    }

    @Override
    public void hide() {
        InputMultiplexer iM = (InputMultiplexer) Gdx.input.getInputProcessor();
        iM.addProcessor(this);
        iM.addProcessor(uiStage);
        iM.addProcessor(mainStage);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float x, float y) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean isTouchDownEvent(Event e) {
        return (e instanceof InputEvent) && ((InputEvent) e).getType().equals(InputEvent.Type.touchDown);
    }

}

package com.avs.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import currentpackage.CurrentPackage;

public class LevelScreen extends BaseScreen {

    private final String PAC_CAT = CurrentPackage.getCurrentPackage(this.getClass()) + ".";
    Paddle paddle;
    Ball ball;
    int score;
    int balls;
    Label scoreLabel;
    Label ballsLabel;
    Label messageLabel;

    public void initialize() {
        score = 0;
        balls = 3;
        scoreLabel = new Label("Score: " + score, BaseGame.labelStyle);
        ballsLabel = new Label("Balls: " + balls, BaseGame.labelStyle);
        messageLabel = new Label("Press space to start!", BaseGame.labelStyle);
        scoreLabel.setColor(Color.GREEN);
        ballsLabel.setColor(Color.GREEN);
        messageLabel.setColor(Color.DARK_GRAY);
        TilemapActor tma = new TilemapActor("resources/rectangleDestroyerMap.tmx", mainStage);
        for (MapObject obj : tma.getTileList("Wall")) {
            MapProperties props = obj.getProperties();
            new Wall((float) props.get("x"), (float) props.get("y"),
                    (float) props.get("width"), (float) props.get("height"),
                    mainStage);
        }
        for (MapObject obj : tma.getTileList("Brick")) {
            MapProperties props = obj.getProperties();
            Brick b = new Brick((float) props.get("x"), (float) props.get("y"), mainStage);
            b.setSize((float) props.get("width"), (float) props.get("height"));
            b.setBoundaryRectangle();
            String colorName = (String) props.get("color");
            if (colorName.equals("Red"))
                b.setColor(Color.RED);
            else if (colorName.equals("Orange"))
                b.setColor(Color.ORANGE);
            else if (colorName.equals("Yellow"))
                b.setColor(Color.YELLOW);
            else if (colorName.equals("Green"))
                b.setColor(Color.GREEN);
            else if (colorName.equals("Blue"))
                b.setColor(Color.BLUE);
            else if (colorName.equals("Purple"))
                b.setColor(Color.PURPLE);
            else if (colorName.equals("White"))
                b.setColor(Color.WHITE);
            else if (colorName.equals("Gray"))
                b.setColor(Color.GRAY);
        }
        MapObject startPoint = tma.getRectangleList("Start").get(0);
        MapProperties props = startPoint.getProperties();
        paddle = new Paddle((float) props.get("x"), (float) props.get("y"), mainStage);
        ball = new Ball(0, 0, mainStage);
        uiTable.pad(5);
        uiTable.add(scoreLabel);
        uiTable.add().expandX();
        uiTable.add(ballsLabel);
        uiTable.row();
        uiTable.add(messageLabel).colspan(3).expandY();
    }

    public void update(float dt) {
        if (BaseActor.count(mainStage, PAC_CAT + "Brick") == 0) {
            messageLabel.setText("You win!");
            messageLabel.setColor(Color.GREEN);
            messageLabel.setVisible(true);
        }
        if (ball.getY() < -50 && BaseActor.count(mainStage, PAC_CAT + "Brick") > 0) {
            ball.remove();
            if (balls > 0) {
                balls -= 1;
                ballsLabel.setText("Balls: " + balls);
                ball = new Ball(0, 0, mainStage);
                messageLabel.setText("Press space to start!");
                messageLabel.setColor(Color.DARK_GRAY);
                messageLabel.setVisible(true);
            } else {
                messageLabel.setText("Game Over");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
            }
        }
        if (ball.isPaused()) {
            ball.setX(paddle.getX() + paddle.getWidth() / 2 - ball.getWidth() / 2);
            ball.setY(paddle.getY() + paddle.getHeight() / 2 - ball.getHeight() / 2);
        }
        for (BaseActor wall : BaseActor.getList(mainStage, PAC_CAT + "Wall")) {
            if (ball.overlaps(wall)) {
                ball.bounceOff(wall);
            }
        }
        for (BaseActor brick : BaseActor.getList(mainStage, PAC_CAT + "Brick")) {
            if (ball.overlaps(brick)) {
                ball.bounceOff(brick);
                Explode boom = new Explode(0, 0, mainStage);
                boom.centerAtActor(brick);
                brick.remove();
                score += 100;
                scoreLabel.setText("Score: " + score);
            }
        }
        if (ball.overlaps(paddle)) {
            float ballCenterX = ball.getX() + ball.getWidth() / 2;
            float paddlePercentHit = (ballCenterX - paddle.getX()) / paddle.getWidth();
            float bounceAngel = MathUtils.lerp(150, 30, paddlePercentHit);
            ball.setMotionAngel(bounceAngel);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (ball.isPaused()) {
            ball.setPaused(false);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (ball.isPaused()) {
                ball.setPaused(false);
            }
        }
        messageLabel.setVisible(false);
        return false;
    }

}
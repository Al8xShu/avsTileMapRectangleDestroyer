package com.avs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Launcher {

    public static void main(String[] args) {
        Game myGame = new CustomGame();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Rectangle Destroyer Game");
        config.setWindowedMode(832, 640);
        new Lwjgl3Application(myGame, config);
    }

}
package com.notjj.opengl.chapter04.game;

import com.notjj.opengl.chapter04.engine.GameEngine;
import com.notjj.opengl.chapter04.engine.IGameLogic;
import com.notjj.opengl.chapter04.engine.Window;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import org.lwjgl.glfw.GLFW;

/**
 * Created by jj on 7/28/17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine engine = new GameEngine("Game", 800, 600, vSync, gameLogic);
            engine.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

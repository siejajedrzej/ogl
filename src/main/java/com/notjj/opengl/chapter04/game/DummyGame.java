package com.notjj.opengl.chapter04.game;

import com.notjj.opengl.chapter04.engine.IGameLogic;
import com.notjj.opengl.chapter04.engine.Window;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;


/**
 * Created by jj on 7/28/17.
 */
public class DummyGame implements IGameLogic{

    private final Renderer renderer;



    public DummyGame(){

        renderer = new Renderer();
    }
    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void input(Window window) {

        if(window.isKeyPressed(GLFW_KEY_UP)){



        }
    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        renderer.render(window);
    }

    @Override
    public void cleanUp() {
        renderer.cleanUp();
    }
}

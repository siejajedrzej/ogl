package com.notjj.opengl.chapter04.engine;

/**
 * Created by jj on 7/28/17.
 */
public interface IGameLogic {

    void init() throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

    void cleanUp();


}

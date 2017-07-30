package com.notjj.opengl.chapter04.engine;

/**
 * Created by jj on 7/28/17.
 */
public class GameEngine implements Runnable {


    /**
     * UPDA$ glxinfo|egrep "OpenGL vendor|OpenGL
TES PER SECOND
     * FRAMES PER SECOND
     */
    private static final int TARGET_FPS = 60;
    private static final int TARGET_UPS = 30;

    private final Window window;
    private final Thread gameLoopThread;
    private final Timer timer;
    private final IGameLogic iGameLogic;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic iGameLogic) throws Exception {

        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        window = new Window(windowTitle, width, height, vSync);
        this.iGameLogic = iGameLogic;
        timer = new Timer();
    }

    public void start() {
        gameLoopThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cleanUp();
        }
    }

    protected void init() throws Exception {

        window.init();
        timer.init();
        iGameLogic.init();
    }

    protected void gameLoop() {

        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
        boolean isRunning = true;

        while (isRunning && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();
            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isvSync()) {
                sync();
            }

        }
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    protected void input() {
        iGameLogic.input(window);
    }

    protected void update(float interval) {
        iGameLogic.update(interval);
    }

    protected void render() {
        iGameLogic.render(window);
        window.update();
    }

    protected void cleanUp(){
        iGameLogic.cleanUp();
    }

}

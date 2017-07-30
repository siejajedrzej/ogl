package com.notjj.opengl.chapter04.engine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by jj on 7/28/17.
 */
public class Window {

    private final String title;
    private int width;
    private int height;
    private long windowHandle;
    private boolean resized;
    private boolean vSync;

    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.resized = false;
    }


    public void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        /**
         * Initialize GLfW
         */
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize glfw");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // WINDOW WILL BE HIDDEN AfTER CReATION
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // WINDOW WILL BE RESIZABLE
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // WINDOW WILL BE RESIZABLE
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2); // WINDOW WILL BE RESIZABLE
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("failed to create glfw window");
        }

        glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
            this.width = width;
            this.height = height;
            this.setResized(true);
        });

        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE) {
                glfwSetWindowShouldClose(window, true);
            }
        })
        ;


        /**
         * getting resolution of primary monitor and centering the screen
         */
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowHandle, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);


        /**
         * Make opengl context current
         */
        glfwMakeContextCurrent(windowHandle);

        /**
         * Enabling vSync
         */
        if (isvSync()) {
            glfwSwapInterval(1);
        }

        glfwShowWindow(windowHandle);
        GL.createCapabilities();

        /**
         * Setting bg color to red I GUESS
         */
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


    }

    public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }


    public void update() {

        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public void setWindowHandle(long windowHandle) {
        this.windowHandle = windowHandle;
    }

    public boolean isResized() {
        return resized;
    }

    public void setResized(boolean resized) {
        this.resized = resized;
    }

    public boolean isvSync() {
        return vSync;
    }

    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }
}

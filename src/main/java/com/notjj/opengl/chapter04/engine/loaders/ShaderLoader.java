package com.notjj.opengl.chapter04.engine.loaders;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ShaderLoader {

    public static String loadResource(String fileName) throws Exception {
        String result;
        try (InputStream in = ShaderLoader.class.getClass().getResourceAsStream(fileName);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
}

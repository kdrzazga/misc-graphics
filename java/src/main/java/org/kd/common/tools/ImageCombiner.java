package org.kd.common.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ImageCombiner {
    public static Texture combineImages(String[] imagePaths) {
        if (imagePaths.length == 0) {
            throw new IllegalArgumentException("At least one image is required.");
        }

        var pixmaps = new Texture[imagePaths.length];
        int totalWidth = 0;
        int maxHeight = 0;

        String pwd = System.getProperty("user.dir") + "\\java\\src\\test\\resources\\";

        for (int i = 0; i < imagePaths.length; i++) {
            String fileName = pwd + imagePaths[i];
            FileHandle handle = Gdx.files.internal(imagePaths[i]);
            pixmaps[i] = new Texture(handle);
            totalWidth += pixmaps[i].getWidth();
            maxHeight = Math.max(maxHeight, pixmaps[i].getHeight());
        }

        Pixmap combinedPixmap = new Pixmap(totalWidth, maxHeight, Pixmap.Format.RGBA8888);
/*
        int currentX = 0;
        for (Pixmap pixmap : pixmaps) {
            combinedPixmap.drawPixmap(pixmap, currentX, 0);
            currentX += pixmap.getWidth();
        }

        Texture combinedTexture = new Texture(combinedPixmap);

        for (Pixmap pixmap : pixmaps) {
            pixmap.dispose();
        }*/
        combinedPixmap.dispose();

        return null;
    }
}

package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Helper {

    static int SPEED = 3;

    public static void moveCursor(Vector2 source, Vector2 destination, long startFrame, long currentFrame){
        if (currentFrame < startFrame){
            System.err.println("Wrong frame numbers");
            return;
        }
        float fullDistance = source.dst(destination);

        long elapsedFrames = currentFrame - startFrame;
        float distanceMoved = elapsedFrames;

        if (distanceMoved > fullDistance) {
            distanceMoved = fullDistance;
        }

        float progress = fullDistance == 0 ? 0 : distanceMoved / fullDistance*SPEED;

        int currentX = Math.round(source.x + (destination.x - source.x) * progress);
        int currentY = Math.round(source.y + (destination.y - source.y) * progress);
        Gdx.input.setCursorPosition(currentX, currentY);
    }

    public static String countElapsedTime() {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - Globals.startTime;
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}

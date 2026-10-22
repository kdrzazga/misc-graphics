package org.kd.common;

/** Largest centred rectangle with the demo's proportions that fits the screen, in screen pixels (origin bottom-left). */
public record Letterbox(int x, int y, int width, int height, float scale) {

    public static Letterbox fit(int demoWidth, int demoHeight, int screenWidth, int screenHeight) {
        float scale = Math.min(screenWidth / (float) demoWidth, screenHeight / (float) demoHeight);
        int width = Math.round(demoWidth * scale);
        int height = Math.round(demoHeight * scale);
        return new Letterbox((screenWidth - width) / 2, (screenHeight - height) / 2, width, height, scale);
    }
}

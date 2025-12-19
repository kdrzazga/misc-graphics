package org.kd.common;

import com.badlogic.gdx.graphics.Color;

public enum C64Colors {
    BLACK(0, 0, 0),
    WHITE(1, 1, 1),
    RED(0.53f,0,0),
    BLUE(0.239f,0.278f, 0.725f),
    YELLOW(1f,1f, 0f),
    LIGHT_BLUE(0.415f, 0.463f, 0.988f),
    CYAN(0f, 1f, 1f),
    PURPLE(1f, 0f, 1f),
    DARK_GRAY(0.2f,0.2f,0.2f),
    GRAY(0.46f,0.46f,0.46f),
    LIGHT_GRAY(0.7f, 0.7f, 0.7f),
    PINK(0.75f, 0.51f, 0.47f);

    private final float r;
    private final float g;
    private final float b;

    C64Colors(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public com.badlogic.gdx.graphics.Color toBadlogicColor(){
        return new Color(this.r, this.g, this.b, 1);
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}

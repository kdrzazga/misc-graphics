package org.kd.common;

import com.badlogic.gdx.graphics.Color;

public enum C64Colors {
    BLACK(0, 0, 0),
    WHITE(1, 1, 1),
    RED(136/255,0,0),
    BLUE(0.239f,0.278f, 0.725f),
    LIGHT_BLUE(0.415f, 0.463f, 0.988f);

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

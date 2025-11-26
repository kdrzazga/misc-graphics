package org.kd.c64.data;

public enum C64Colors {
    BLACK(0, 0, 0),
    WHITE(1, 1, 1),
    RED(136/255,0,0),
    BLUE(0,0, 170/255),
    LIGHT_BLUE(0, 136/255, 1);

    private final float r;
    private final float g;
    private final float b;

    C64Colors(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
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

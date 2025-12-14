package org.kd.common;

public class Globals {
    static final public int SCREEN_WIDTH = 800;//1920;
    static final public int SCREEN_HEIGHT = 600;//1080;
    static final public long DEFAULT_CURSOR_Y = Math.round(0.647 * Globals.SCREEN_HEIGHT) - 2;

    static public C64Colors CURSOR_COLOR = C64Colors.LIGHT_BLUE;
    static public C64Colors BKG_COLOR = C64Colors.BLUE;
    static public long cursorY = DEFAULT_CURSOR_Y;

    static public final long startTime = System.currentTimeMillis();
}

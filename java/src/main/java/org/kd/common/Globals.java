package org.kd.common;

import com.badlogic.gdx.Gdx;

public class Globals {
    static public int SCREEN_WIDTH = 800;
    static public int SCREEN_HEIGHT = 600;

    static public C64Colors CURSOR_COLOR = C64Colors.LIGHT_BLUE;
    static public C64Colors BKG_COLOR = C64Colors.BLUE;
    static public long cursorY = Math.round(0.647* Globals.SCREEN_HEIGHT) - 2;
}

package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class C64Helper {

    public static void blinkCursor(long frame, ShapeRenderer shapeRenderer) {
        long cond = frame % 100;
        C64Colors c = cond > 0 && cond <= 50 ? Globals.CURSOR_COLOR : Globals.BKG_COLOR;

        paintCursor(c, shapeRenderer);
    }

    public static void paintCursor(C64Colors c, ShapeRenderer shapeRenderer) {
        long curX = Math.round(0.102 * Gdx.graphics.getWidth()) - 1;
        long curY = Globals.cursorY;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(c.getR(), c.getG(), c.getB(), 1);
        shapeRenderer.box(curX, curY, 0, 14, 15, 0);
        shapeRenderer.end();
    }

    public static BitmapFont createFont(int size, String ttfFile) {
        return createFont(size, C64Colors.WHITE, ttfFile);
    }

    public static BitmapFont createFont(int size, C64Colors color, String ttfFile) {
        var generator = new FreeTypeFontGenerator(Gdx.files.internal(ttfFile));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size; // font size
        BitmapFont font = generator.generateFont(parameter);
        font.setColor(color.getR(), color.getG(), color.getB(), 1f);
        generator.dispose();
        return font;
    }
}

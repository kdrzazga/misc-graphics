package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class C64Helper {

    public static void blinkCursor(long frame, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        long curX = Math.round(0.102*Gdx.graphics.getWidth()) - 1;
        long curY = Math.round(0.647*Gdx.graphics.getHeight()) - 2;

        long cond = frame % 100;
        C64Colors c = cond > 0 && cond <= 50 ? C64Colors.LIGHT_BLUE : C64Colors.BLUE;

        shapeRenderer.setColor(c.getR(), c.getG(), c.getB(), 1);
        shapeRenderer.box(curX, curY, 0, 14, 15, 0);
        shapeRenderer.end();

    }
}

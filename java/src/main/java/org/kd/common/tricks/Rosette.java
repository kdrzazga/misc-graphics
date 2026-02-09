package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rosette {

    private final long startFrame;
    private final Color color;
    float coeff = 1;

    public Rosette(long startFrame, Color color) {
        this.startFrame = startFrame;
        this.color = color;
    }

    public void render(ShapeRenderer shapeRen) {
        float maxX = Gdx.graphics.getWidth();
        float maxY = Gdx.graphics.getHeight();

        int fr = Long.valueOf(Gdx.graphics.getFrameId() - this.startFrame).intValue();
        shapeRen.setColor(this.color);

        shapeRen.begin(ShapeRenderer.ShapeType.Filled);
        shapeRen.triangle(
                maxX / 2, maxY / 2,
                0, 0,
                coeff * fr, 0
        );
        shapeRen.triangle(
                maxX / 2, maxY / 2,
                0, maxY,
                coeff * fr, maxY
        );

        shapeRen.triangle(
                maxX / 2 + 1, maxY / 2 + 1,
                0, coeff*fr,
                1, 0
        );

        coeff += 0.1f;
        if (coeff * fr > 6 * maxX) coeff += 300;
        shapeRen.end();
    }
}

package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rosette {

    private final long startFrame;
    private final long relEndFrame = 250;
    private final Color color;
    float coeff = 1;

    public Rosette(long startFrame, Color color) {
        this.startFrame = startFrame;
        this.color = color;
    }

    public void render(ShapeRenderer shapeRen) {
        int fr = Long.valueOf(Gdx.graphics.getFrameId() - this.startFrame).intValue();
        if (fr > relEndFrame) {
            System.out.println(Rosette.class.getSimpleName() + " effect done");
            return;
        }
        float maxX = Gdx.graphics.getWidth();
        float maxY = Gdx.graphics.getHeight();
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
                0, coeff * fr,
                1, 0
        );

        coeff += 0.1f;
        if (coeff * fr > 1.5 * maxX) {
            coeff += 1;
            shapeRen.rect(0, 0, 2*fr + 3*coeff, maxY);
        }
        if (coeff * fr > 3 * maxX) coeff += 1;
        if (coeff * fr > 4.5 * maxX) coeff += 1;
        if (coeff * fr > 5 * maxX) coeff += 10;

        shapeRen.end();
    }

    public long getStartFrame() {
        return startFrame;
    }
}

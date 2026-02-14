package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RollerShades {

    private final long startFrame;
    private final long relEndFrame = 750;
    private final int linesCount;
    private final Color color;

    public RollerShades(long startFrame, Color color, int linesCount) {
        this.startFrame = startFrame;
        this.color = color;
        this.linesCount = Math.max(1, linesCount);
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
        int step = Math.round(maxY / linesCount);

        for (int y = 0; y < maxY; y += 2 * step) {
            shapeRen.rect(0, y, 5 * fr, step + 1);
        }
        for (int y = step; y < maxY; y += 2 * step) {
            shapeRen.rect(maxX - 5 * fr, y, 5 * fr, step + 1);
        }

        shapeRen.end();
    }

    public long getStartFrame() {
        return startFrame;
    }
}

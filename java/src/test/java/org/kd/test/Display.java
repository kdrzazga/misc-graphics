package org.kd.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.tricks.letters.Letter;
import org.kd.tricks.letters.LetterA;

public class Display extends ApplicationAdapter {
    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        super.render();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        var x1 = 50;
        var y1 = 100;

        var a = new LetterA(8);

        draw(a, y1, x1);
        shapeRenderer.end();
    }

    private void draw(Letter a, int y1, int x1) {
        int size = a.getPattern()[0][0].size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                final float X = x;
                final float Y = y;
                a.getPattern()[y][x].getPoints().forEach(point -> {
                    var y2 = y1 - (point.getY() + size * Y);
                    shapeRenderer.rect(x1 + point.getX() + size * X, y2, 1, 1);
                });
            }
        }
    }
}

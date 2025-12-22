package org.kd.org.kd.trick1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.List;

public class StarsArray {

    private final int width;
    private final List<Point> stars;

    public StarsArray(int width, int height) {
        this.width = width;
        stars = new ArrayList<>(2 * height);

        for (int i = 0; i < height; i++) {
            var x1 = -0.2 * width * Math.random();
            var p1 = new Point((int) x1, i);

            var x2 = x1 + 0.2 * width + 10 * i / height;
            var p2 = new Point((int) x2, i);

            stars.add(p1);
            stars.add(p2);
        }

    }

    public void move() {
        stars.forEach(star -> {
            var speed = 1 + 3 * Math.random();
            var newX = Long.valueOf(Math.round(star.getX() + speed)).intValue();
            star.setX(newX);
            if (star.getX() > width) star.setX(0);
        });
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        var maxX = Gdx.graphics.getWidth();
        stars.stream()
                .filter(stars -> stars.getX() > 0 && stars.getX() < maxX)
                .forEach(star -> shapeRenderer.rect(star.getX(), star.getY(), 1, 1));
    }
}

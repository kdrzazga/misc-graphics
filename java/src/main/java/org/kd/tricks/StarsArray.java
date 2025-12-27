package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.List;

public class StarsArray {


  public double spread = 1.2;
    public double spread2 = 20;

    private final int width;
    private final List<Point> stars;

    public StarsArray(int width, int height) {
        this.width = width;
        stars = new ArrayList<>(2 * height);

        for (int i = 0; i < height; i++) {

           var x1 = -spread * width * Math.random();
            var p1 = new Point((int) x1, i);

            var x2 = x1 + spread * width + spread2 * i / height;

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

    public void draw(ShapeRenderer shapeRenderer, int x1, int y1, int x2, int y2) {
        shapeRenderer.setColor(Color.WHITE);
        var maxX = Math.min(x2, Gdx.graphics.getWidth());
        var maxY = Math.min(y2, Gdx.graphics.getHeight());
        stars.stream()
                .filter(stars -> stars.getX() > x1 && stars.getX() < maxX)
                .filter(stars -> stars.getY() > y1 && stars.getY() < maxY)
                .forEach(star -> shapeRenderer.rect(star.getX(), star.getY(), 1, 1));
    }
}

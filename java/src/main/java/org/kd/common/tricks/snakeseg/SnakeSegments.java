package org.kd.common.tricks.snakeseg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class SnakeSegments {

    private final float segmentRadius;
    private final List<Segment> segments;

    public SnakeSegments(int segmentCount) {
        this(segmentCount, 40f);
    }

    public SnakeSegments(int segmentCount, float segmentRadius) {
        this.segmentRadius = segmentRadius;
        segments = new ArrayList<>(segmentCount);
        for (int i = 0; i < segmentCount; i++) {
            var s = new Segment(segmentRadius + i * segmentRadius * 2, segmentRadius);
            segments.add(s);
        }
    }

    public void move(float dx, float dy) {
        for (int i = 0; i < segments.size() - 1; i++) {
            var s1 = segments.get(i);
            var s2 = segments.get(i + 1);
            var middlePointX = (s1.x + s2.x) / 2;
            var middlePointY = (s1.y + s2.y) / 2;

            s2.moveTowards(middlePointX, middlePointY);
        }

        segments.get(0).moveTowards(dx, dy);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.circle(segments.get(0).x, segments.get(0).y, segmentRadius);
        shapeRenderer.setColor(Color.WHITE);
        for (int i = 1; i < segments.size(); i++)
            shapeRenderer.circle(segments.get(i).x, segments.get(i).y, segmentRadius);
        shapeRenderer.end();
    }


    private static class Segment {
        public float x, y;

        Segment(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void moveTowards(float xDest, float yDest) {
            var speed = 0.1;

            var dx = xDest - this.x;
            var dy = yDest - this.y;

            var angle = Math.atan2(dy, dx);

            var d = Math.sqrt(dx * dx + dy * dy) * speed;

            this.x += d * Math.cos(angle);
            this.y += d * Math.sin(angle);
        }
    }
}

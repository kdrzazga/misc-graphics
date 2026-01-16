package org.kd.tricks.snakeseg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.tricks.snakeseg.SnakeSegments;

public class Display extends ApplicationAdapter {

    private SnakeSegments s;
    private ShapeRenderer shapeRenderer;
    private float angle = 0;

    @Override
    public void create() {
        s = new SnakeSegments(19, 26);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        s.render(shapeRenderer);

        var f = Gdx.graphics.getFrameId();
        //System.out.print(f + " ");

        var r = 300;
        if (f % 4 == 0) {

            if (f < 120)
                s.move(300, 400);
            else if (f < 240)
                s.move(750, 550);
            else if (f < 380)
                s.move(60, 590);
            else if (f < 650)
                s.move(90, 50);
            else if (f < 850)
                s.move(750, 350);
            else if (f < 1150)
                s.move(40, 350);

            else {
                double x = 400 +r * Math.cos(angle);
                double y = 300 +r * Math.sin(angle);
                s.move((float) x, (float) y);
                angle += 0.02f;
                System.out.print(r*Math.cos(angle) + " " + angle + " ");
            }

        }
    }
}

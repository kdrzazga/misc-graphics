package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.C64Helper;
import org.kd.common.Scene;
import org.kd.tricks.GradientRectangleTrick;
import org.kd.tricks.StarsArray;

public final class Scene2 extends Scene {

    public static final long START_FRAME = 2180;

    private SpriteBatch batch;
    private Sprite jobsWayneWoz;
    private GradientRectangleTrick gradientRectangle;
    private ShapeRenderer shapeRenderer;
    private StarsArray starsArray;
    private BitmapFont fontSmall;

    public Scene2() {
        super("2");
    }

    @Override
    public void create() {
        var jobsWayneWozTexture = new Texture("good-job\\jobsWayneWoz.jpg");
        batch = new SpriteBatch();
        this.jobsWayneWoz = new Sprite(jobsWayneWozTexture);
        this.jobsWayneWoz.setScale(1.8f);

        this.shapeRenderer = new ShapeRenderer();
        this.gradientRectangle = new GradientRectangleTrick(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), batch, shapeRenderer);

        starsArray = new StarsArray(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        starsArray.spread = 0.75;

        this.fontSmall = C64Helper.createFont(50, "Helvetica Regular.otf");
    }

    @Override
    public void update(float delta) {
        long frame = Gdx.graphics.getFrameId();
        this.gradientRectangle.update(false, false, true);
        int speed = Math.toIntExact(Math.round(7.5 + 3.5 * Math.sin(frame / 100f * Math.PI)));
        starsArray.move(speed);

        System.out.print(this.getRelativeFrame() + " ");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (this.getRelativeFrame() > 500) {

            batch.draw(this.jobsWayneWoz, 0, Gdx.graphics.getHeight() / 2f - 50);
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.starsArray.draw(shapeRenderer, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        //this.gradientRectangle.drawGradientRectangle(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}

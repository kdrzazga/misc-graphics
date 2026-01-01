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

    public static final long START_FRAME = 2200;

    private SpriteBatch batch;
    private Sprite ronaldWayne, steveWozniak, steveJobs;
    private GradientRectangleTrick gradientRectangle;
    private ShapeRenderer shapeRenderer;
    private StarsArray starsArray;
    private BitmapFont fontSmall;

    public Scene2() {
        super("2");
    }

    @Override
    public void create() {
        var wayneTexture = new Texture("good-job/RonaldWayne.png");
        var wozniakTexture = new Texture("good-job/SteveWozniak.png");
        var jobsTexture = new Texture("good-job/SteveJobs.png");
        Texture threeAmigos = new Texture("good-job/jobsWayneWoz.jpg");

        batch = new SpriteBatch();
        this.ronaldWayne = new Sprite(wayneTexture);
        this.ronaldWayne.setScale(0.5f, 0.5f);
        this.steveWozniak = new Sprite(wozniakTexture);
        this.steveWozniak.setScale(0.5f, 0.5f);
        this.steveJobs = new Sprite(jobsTexture);
        this.steveJobs.setScale(0.5f, 0.5f);

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
        //int speed = Math.toIntExact(Math.round(7.5 + 3.5 * Math.sin(frame / 100f * Math.PI)));
        starsArray.move(6);

        System.out.print(this.getRelativeFrame() + " ");

        if (this.getRelativeFrame() > 500) {
            if (this.getRelativeFrame() < 778) {
                var newScale = Math.min(2f, this.ronaldWayne.getScaleX() + 0.01f);
                this.ronaldWayne.setScale(newScale, newScale);
            } else if (this.getRelativeFrame() < 1000) {
                var newScale = Math.min(2f, this.steveWozniak.getScaleX() + 0.01f);
                this.steveWozniak.setScale(newScale, newScale);
            } else if (this.getRelativeFrame() < 1280) {
                var newScale = Math.min(2f, this.steveJobs.getScaleX() + 0.01f);
                this.steveJobs.setScale(newScale, newScale);
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (this.getRelativeFrame() > 500) {
            if (this.getRelativeFrame() < 778) {
                drawFounder(this.ronaldWayne, "Ronald Wayne", 0);
            } else if (this.getRelativeFrame() < 1000) {
                drawFounder(this.steveWozniak, "Steve Wozniak", 240);
            } else if (this.getRelativeFrame() < 1280) {
                drawFounder(this.steveJobs, "Steve Jobs", 480);
            }
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.starsArray.draw(shapeRenderer, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    private void drawFounder(Sprite founder, String caption, float shiftX) {
        var founderX = Gdx.graphics.getWidth() / 2f - founder.getScaleX() * this.ronaldWayne.getWidth() + shiftX;
        founder.setPosition(founderX, Gdx.graphics.getHeight() / 2f - 240);
        founder.draw(batch);
        fontSmall.draw(batch, caption, Gdx.graphics.getWidth() / 2f - 20, 50);
    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}

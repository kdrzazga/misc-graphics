package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import org.kd.common.C64Helper;
import org.kd.common.ConsoleLogger;
import org.kd.common.Scene;
import org.kd.goodjob.appendix.BannerApple;
import org.kd.tricks.GradientRectangleTrick;
import org.kd.tricks.StarsArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Scene2 extends Scene {

    public static final long START_FRAME = 2200;

    private SpriteBatch batch;
    private Sprite ronaldWayne, steveWozniak, steveJobs;
    private List<Sprite> threeAmigosSprites;
    private Texture threeAmigos, apple1, apple2, macintosh;
    private List<Sprite> apple1Sprites;
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
        threeAmigos = new Texture("good-job/3amigos/jobsWayneWoz.jpg");
        apple1 = new Texture("good-job/apple1/Apple1.jpg");
        apple2 = new Texture("good-job/apple2/apple2.jpg");
        macintosh = new Texture("good-job/macintosh/jobs-macintosh.jpg");

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

        threeAmigosSprites = new ArrayList<>(10);
        IntStream.range(0, 9 + 1).forEach(i -> {
            var t = new Texture("good-job/3amigos/pic" + i + ".png");
            var s = new Sprite(t);
            s.setScale(0.02f, 1f);
            threeAmigosSprites.add(s);
        });

        apple1Sprites = new ArrayList<>(10);
        IntStream.range(0, 9 + 1).forEach(i -> {
            var t = new Texture("good-job/apple1/pic" + i + ".png");
            var s = new Sprite(t);
            s.setScale(0.02f, 1f);
            apple1Sprites.add(s);
        });
    }

    @Override
    public void update(float delta) {
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

            if (this.getRelativeFrame() < 1600)
                amigosGoRound(this.threeAmigosSprites, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
            else if (this.getRelativeFrame() < 2100)
                bringAmigosTogether(this.threeAmigosSprites, Gdx.graphics.getHeight() / 3f, Gdx.graphics.getWidth() / 4f);
            else if (this.getRelativeFrame() < 2250)
                amigosGoRound(this.apple1Sprites, 3 * Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 2 - 30);
            else
                bringAmigosTogether(this.apple1Sprites, 3 * Gdx.graphics.getHeight() / 5f, Gdx.graphics.getWidth() / 2f - 30);
        }

        if (1360 < this.getRelativeFrame() && this.getRelativeFrame() < 1800) {
            restoreSize(this.threeAmigosSprites);
        }
        else if (2160 < this.getRelativeFrame() && this.getRelativeFrame() < 2700) {
            restoreSize(this.apple1Sprites);
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

        if (1350 < this.getRelativeFrame() && this.getRelativeFrame() < 1950) {
            this.threeAmigosSprites.forEach(sprite -> sprite.draw(batch));
        } else if (1950 < this.getRelativeFrame())
            batch.draw(this.threeAmigos, this.threeAmigosSprites.get(0).getX(), this.threeAmigosSprites.get(0).getY());
        if (2100 < this.getRelativeFrame() && this.getRelativeFrame() < 2700)
            this.apple1Sprites.forEach(sprite -> sprite.draw(batch));
        else if (2700 < this.getRelativeFrame())
            batch.draw(this.apple1, this.apple1Sprites.get(0).getX(), this.apple1Sprites.get(0).getY());

        /*if (2100 < this.getRelativeFrame() && this.getRelativeFrame() < 2700)
            this.apple1Sprites.forEach(sprite -> sprite.draw(batch));
        else*/ if (3400 < this.getRelativeFrame())
            batch.draw(this.apple2, 350, 720);
        /*if (2100 < this.getRelativeFrame() && this.getRelativeFrame() < 2700)
            this.apple1Sprites.forEach(sprite -> sprite.draw(batch));
        else*/ if (5000 < this.getRelativeFrame())
            batch.draw(this.macintosh, 350, 120);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.starsArray.draw(shapeRenderer, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        ConsoleLogger.logBannerWithElapsedTime(BannerApple.lines);
    }

    private void drawFounder(Sprite founder, String caption, float shiftX) {
        var founderX = Gdx.graphics.getWidth() / 2f - founder.getScaleX() * this.ronaldWayne.getWidth() + shiftX;
        founder.setPosition(founderX, Gdx.graphics.getHeight() / 2f - 240);
        founder.draw(batch);
        fontSmall.draw(batch, caption, Gdx.graphics.getWidth() / 2f - 20, 50);
    }

    private void amigosGoRound(List<Sprite> sprites, int x0, int y0) {
        final int r1 = Gdx.graphics.getWidth() / 4;
        final int r2 = Gdx.graphics.getHeight() / 4;

        String frameStr = Long.toString(getRelativeFrame() + 1000);
        String firstTwoDigitsStr = frameStr.length() >= 4 ? frameStr.substring(2, 4) : frameStr;
        int firstTwoDigits = Integer.parseInt(firstTwoDigitsStr);

        final double angle = Math.PI * firstTwoDigits / 50;
        for (int i = 0; i < sprites.size(); i++) {
            var x = (float) (x0 + r1 * Math.cos(angle + i * 2 * Math.PI / 7));
            var y = (float) (y0 + r2 * Math.sin(angle + i * 2 * Math.PI / 7));
            sprites.get(i).setPosition(x, y);
        }
    }

    private void restoreSize(List<Sprite> sprites) {
        sprites.forEach(sprite -> {
            var sc = sprite.getScaleX();
            sc = Math.min(1f, sc + 0.02f);
            sprite.setScale(sc, 1f);
        });
    }

    private void bringAmigosTogether(List<Sprite> sprites, float destY, float destX0) {
        float width = sprites.get(3).getWidth();

        for (int i = 0; i < sprites.size(); i++) {
            var s = sprites.get(i);

            var decY = Math.abs(s.getY() - destY) > 13 ? 5 : 1;
            if (s.getY() > destY) s.setY(s.getY() - decY);
            else if (s.getY() < destY) s.setY(s.getY() + decY);

            float destX = destX0 + i * width;
            var dX = Math.abs(s.getX() - destX) > 13 ? 5 : 1;
            if (s.getX() > destX) s.setX(s.getX() - dX);
            else if (s.getX() < destX) s.setX(s.getX() + dX);
        }

    }

    @Override
    public void dispose() {

    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}

package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import org.kd.tricks.StarsArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Scene2Stars extends Scene {

    public static final long START_FRAME = 2222;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite ronaldWayne, steveWozniak, steveJobs, asciiColor, asciiBlack;
    private List<Sprite> threeAmigosSprites, apple1Sprites;
    private Texture threeAmigos, apple1, apple2, macintosh, noSnow, asciiWhite;
    private StarsArray starsArray;
    private BitmapFont fontSmall, fontSmaller;

    public Scene2Stars() {
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
        asciiWhite = new Texture("good-job/apple2/asciiWHT.png");
        asciiBlack = new Sprite(new Texture("good-job/apple2/asciiBLK.png"));
        asciiColor = new Sprite(new Texture("good-job/apple2/asciiCOL.png"));

        macintosh = new Texture("good-job/macintosh/jobs-macintosh.jpg");
        noSnow = new Texture("good-job/macintosh/nosnow.png");

        batch = new SpriteBatch();
        this.ronaldWayne = new Sprite(wayneTexture);
        this.ronaldWayne.setScale(0.5f, 0.5f);
        this.steveWozniak = new Sprite(wozniakTexture);
        this.steveWozniak.setScale(0.5f, 0.5f);
        this.steveJobs = new Sprite(jobsTexture);
        this.steveJobs.setScale(0.5f, 0.5f);

        this.shapeRenderer = new ShapeRenderer();
        starsArray = new StarsArray(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        starsArray.spread = 0.75;

        this.fontSmall = C64Helper.createFont(45, "Helvetica Regular.otf");
        this.fontSmaller = C64Helper.createFont(40, "Helvetica Regular.otf");

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

        if (this.getRelativeFrame() > 5029)
            return;
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
                picFragmentsGoRound(this.threeAmigosSprites, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
            else if (this.getRelativeFrame() < 2100)
                gluePictureFragments(this.threeAmigosSprites, Gdx.graphics.getHeight() / 3f, Gdx.graphics.getWidth() / 4f);
            else if (this.getRelativeFrame() < 2250)
                picFragmentsGoRound(this.apple1Sprites, 3 * Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 2 - 30);
            else
                gluePictureFragments(this.apple1Sprites, 3 * Gdx.graphics.getHeight() / 5f, Gdx.graphics.getWidth() / 2f - 30);
        }

        if (1360 < this.getRelativeFrame() && this.getRelativeFrame() < 1800) {
            restoreSize(this.threeAmigosSprites);
        } else if (2160 < this.getRelativeFrame() && this.getRelativeFrame() < 2700) {
            restoreSize(this.apple1Sprites);
        }
    }

    @Override
    public void render() {
        if (this.getRelativeFrame() > 5029)
            return;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (this.getRelativeFrame() > 500) {
            if (this.getRelativeFrame() < 778) {
                drawFounder(this.ronaldWayne, "Ronald Wayne", 140);
            } else if (this.getRelativeFrame() < 1000) {
                drawFounder(this.steveWozniak, "Steve Wozniak", 340);
            } else if (this.getRelativeFrame() < 1280) {
                drawFounder(this.steveJobs, "Steve Jobs", 580);
            }
        }

        if (1350 < this.getRelativeFrame() && this.getRelativeFrame() < 1699) {
            this.threeAmigosSprites.forEach(sprite -> sprite.draw(batch));
        } else if (1699 < this.getRelativeFrame()) {
            batch.draw(this.threeAmigos, this.threeAmigosSprites.get(0).getX(), this.threeAmigosSprites.get(0).getY());
            if (this.getRelativeFrame() < 2100)
                fontSmaller.draw(batch, "Wayne didn't believe in APPLE and left soon after co-founding the company.", 30, 45);
        }
        if (2100 < this.getRelativeFrame() && this.getRelativeFrame() < 2418)
            this.apple1Sprites.forEach(sprite -> sprite.draw(batch));
        else if (2418 < this.getRelativeFrame()) {
            batch.draw(this.apple1, this.apple1Sprites.get(0).getX(), this.apple1Sprites.get(0).getY());
            if (this.getRelativeFrame() < 3400)
                fontSmall.draw(batch, "Apple 1 was assembled in Jobs's garage in Los Altos, CA in 1976.", 30, 45);
        }

        if (2600 < this.getRelativeFrame()) {
            if (this.getRelativeFrame() < 2900) {
                asciiBlack.setPosition(70f, 375f);
                asciiBlack.draw(batch);
                float color = this.getRelativeFrame() - 2600;
                asciiBlack.setColor(new Color(color / 300f, color / 300f, color / 300f, color / 300f));
            } else if (getRelativeFrame() < 3150) {
                if (getRelativeFrame() % 20 > 10)
                    batch.draw(asciiWhite, 70f, 375f);
                else
                    asciiBlack.draw(batch);
            } else if (getRelativeFrame() <= 3400) {
                asciiColor.setPosition(70f, 375f);
                asciiColor.draw(batch);
                var scaleX = Math.max(0.5f, asciiColor.getScaleX() * 0.995f);
                var scaleY = Math.max(0.6f, asciiColor.getScaleY() * 0.995f);

                asciiColor.setScale(scaleX, scaleY);
            }
        }

        if (3400 < this.getRelativeFrame()) {
            batch.draw(this.apple2, 70, 475);
            if (this.getRelativeFrame() < 4200) fontSmall.draw(batch, "Apple II was released in June 1977.", 30, 45);
        }

        /*if (2100 < this.getRelativeFrame() && this.getRelativeFrame() < 2700)
            this.apple1Sprites.forEach(sprite -> sprite.draw(batch));
        else*/
        if (4200 < this.getRelativeFrame()) {
            var macX = 310;
            var macY = 122;
            batch.draw(this.macintosh, macX, macY);
            if (this.getRelativeFrame() < 5029) {
                fontSmall.draw(batch, "Macintosh appeared in January 1984", 30, 45);
                if (this.getRelativeFrame() > 4859) batch.draw(noSnow, macX - 190, macY + 228);
            }
        }

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

    private void picFragmentsGoRound(List<Sprite> sprites, int x0, int y0) {
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

    private void gluePictureFragments(List<Sprite> sprites, float destY, float destX0) {
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
        batch.dispose();
        shapeRenderer.dispose();
        threeAmigosSprites.clear();
        apple1Sprites.clear();
        this.starsArray = null;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }
}

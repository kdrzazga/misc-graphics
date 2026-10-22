package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

import java.util.List;

public final class Year1982 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final String picturesPath = "anniversaries27/pics/1982/";

    // Stage start frames, relative to the start of the year
    private final long sunFrame = 10;
    private final long keepingUpFrame = 1021;
    private final long cdFrame = 1780;
    private final long gamesFrame = 2099;
    private final long yearFrames = 2446;

    private final long fadeInFrames = 15;

    // Sun logos cut from sun-logos.png (picture pixels, origin top-left), shown one after another over the campus
    private final List<FittedPicture.Part> sunLogoParts = List.of(
            new FittedPicture.Part(6, 5, 187, 58),   // WORKSTATION plate
            new FittedPicture.Part(6, 88, 187, 74),  // purple sun microsystems
            new FittedPicture.Part(7, 164, 184, 78)); // blue Sun microsystems
    private final float sunLogoWidth = 480;
    private final float sunLogoPadding = 16;

    private final FittedPicture sunCampus;
    private final List<TextureRegion> sunLogos;
    private final VideoClip keepingUp;
    private final List<FittedPicture> compactDiscs, games;

    Year1982(long startingFrame) {
        super("anniversaries27/1982.mp3", startingFrame);
        this.endFrame = startingFrame + yearFrames; // 1982.mp3 lasts 40.752 s at 60 fps

        sunCampus = new FittedPicture(picturesPath + "sun-hq.png", canvas, Color.BLACK, true);
        var sunLogosTexture = new Texture(picturesPath + "sun-logos.png");
        sunLogosTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sunLogos = sunLogoParts.stream()
                .map(part -> new TextureRegion(sunLogosTexture, part.x(), part.y(), part.width(), part.height()))
                .toList();

        // 12.7 s, 480x360 video fitted to the canvas height: 724x543, centred horizontally
        keepingUp = new VideoClip("anniversaries27/video/keeping-up.mp4", 30, 140, 112, 724, 543, false);

        compactDiscs = List.of(
                new FittedPicture(picturesPath + "cd1.png", canvas, Color.BLACK),
                new FittedPicture(picturesPath + "cd2.png", canvas, Color.BLACK),
                new FittedPicture(picturesPath + "cd3.png", canvas, Color.BLACK));
        games = List.of(
                new FittedPicture(picturesPath + "ea.png", canvas, Color.WHITE),
                new FittedPicture(picturesPath + "RA.png", canvas, Color.BLACK));
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        if (sunFrame <= relFrame && relFrame < keepingUpFrame)
            drawSun(batch, relFrame);
        else if (keepingUpFrame <= relFrame && relFrame < cdFrame)
            drawKeepingUp(batch);
        else if (cdFrame <= relFrame && relFrame < gamesFrame)
            drawSlideShow(batch, compactDiscs, relFrame, cdFrame, gamesFrame);
        else if (relFrame >= gamesFrame)
            drawSlideShow(batch, games, relFrame, gamesFrame, yearFrames);

        log(relFrame);

        writeYear(batch);
    }

    /** Sun campus with the three Sun logos appearing one after another on a white plate in its centre. */
    private void drawSun(SpriteBatch batch, long relFrame) {
        batch.begin();
        sunCampus.drawWhole(batch, fadeIn(relFrame, sunFrame));
        batch.end();

        long logoFrames = (keepingUpFrame - sunFrame) / sunLogos.size();
        int logoIndex = (int) Math.min(sunLogos.size() - 1, (relFrame - sunFrame) / logoFrames);
        long logoFrame = sunFrame + logoIndex * logoFrames;
        float alpha = fadeIn(relFrame, logoFrame);

        TextureRegion logo = sunLogos.get(logoIndex);
        float height = sunLogoWidth * logo.getRegionHeight() / logo.getRegionWidth();
        float left = canvas.x + (canvas.width - sunLogoWidth) / 2;
        float bottom = canvas.y + (canvas.height - height) / 2;

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, alpha);
        shapeRenderer.rect(left - sunLogoPadding, bottom - sunLogoPadding,
                sunLogoWidth + 2 * sunLogoPadding, height + 2 * sunLogoPadding);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(logo, left, bottom, sunLogoWidth, height);
        batch.setColor(Color.WHITE);
        batch.end();
    }

    private void drawKeepingUp(SpriteBatch batch) {
        batch.begin();
        keepingUp.draw(batch);
        batch.end();
    }

    /** Pictures share the stage time equally, each fading in over its own background. */
    private void drawSlideShow(SpriteBatch batch, List<FittedPicture> pictures, long relFrame,
                               long firstFrame, long endFrame) {
        long pictureFrames = (endFrame - firstFrame) / pictures.size();
        int pictureIndex = (int) Math.min(pictures.size() - 1, (relFrame - firstFrame) / pictureFrames);
        FittedPicture picture = pictures.get(pictureIndex);

        picture.fillArea(shapeRenderer);
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, firstFrame + pictureIndex * pictureFrames));
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) fadeInFrames));
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == sunFrame) {
            System.out.print("Sun ");
        } else if (relFrame == keepingUpFrame) {
            System.out.print("C64 ");
        } else if (relFrame == cdFrame) {
            System.out.print("CD ");
        } else if (relFrame == gamesFrame) {
            System.out.println("EA,RA ");
        }
    }

}

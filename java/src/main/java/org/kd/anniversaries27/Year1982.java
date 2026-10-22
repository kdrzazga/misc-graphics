package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private final float plateWhiteThreshold = 0.92f; // around the plate, pixels this white become transparent

    private final FittedPicture sunCampus;
    private final List<TextureRegion> sunLogos;
    private final VideoClip keepingUp;
    private final List<FittedPicture> compactDiscs, games;

    Year1982(long startingFrame) {
        super("anniversaries27/1982.mp3", startingFrame);
        this.endFrame = startingFrame + yearFrames; // 1982.mp3 lasts 40.752 s at 60 fps

        sunCampus = new FittedPicture(picturesPath + "sun-hq.png", canvas, Color.BLACK, true);
        var sunLogosTexture = withoutWhiteBackground(picturesPath + "sun-logos.png");
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

    /**
     * The picture with its white background made transparent. The coloured logos lose white the way GIMP's
     * "colour to alpha" does, so their anti-aliased edges stay soft; the silver WORKSTATION plate would turn
     * see-through that way, so around it only the near-white pixels are cleared.
     */
    private Texture withoutWhiteBackground(String path) {
        var original = new Pixmap(Gdx.files.internal(path));
        var pixmap = new Pixmap(original.getWidth(), original.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.drawPixmap(original, 0, 0);
        original.dispose();

        var plate = sunLogoParts.getFirst();
        for (int y = 0; y < pixmap.getHeight(); y++)
            for (int x = 0; x < pixmap.getWidth(); x++) {
                int rgba = pixmap.getPixel(x, y);
                int red = rgba >>> 24, green = (rgba >>> 16) & 0xff, blue = (rgba >>> 8) & 0xff;
                float originalAlpha = (rgba & 0xff) / 255f;
                boolean onPlate = plate.x() <= x && x < plate.x() + plate.width()
                        && plate.y() <= y && y < plate.y() + plate.height();
                float whiteness = Math.min(red, Math.min(green, blue)) / 255f;
                float alpha = onPlate ? (whiteness >= plateWhiteThreshold ? 0 : 1) : 1 - whiteness;
                if (alpha <= 0) {
                    pixmap.drawPixel(x, y, 0);
                    continue;
                }
                // what is left of each channel once the white mixed into it is taken out
                pixmap.drawPixel(x, y, Color.rgba8888(withoutWhite(red, alpha), withoutWhite(green, alpha),
                        withoutWhite(blue, alpha), alpha * originalAlpha));
            }

        var texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private float withoutWhite(int channel, float alpha) {
        return 1 - (1 - channel / 255f) / alpha;
    }

    /** Sun campus with the three Sun logos appearing one after another in its centre. */
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

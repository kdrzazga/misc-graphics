package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.FittedPicture;
import org.kd.common.VideoClip;

import java.util.ArrayList;
import java.util.List;

public final class Year1997 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle kasperskyArea = new Rectangle(154, 470, 697, 160);
    private final String picturesPath = "anniversaries27/pics/1997/";
    private final String videosPath = "anniversaries27/video/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 1997.mp3.
    private final long yahooFrame = seconds(4);           // right after the 3.97 s Windows 98 video
    private final long postgresqlFrame = seconds(10);
    private final long kasperskyFrame = seconds(17);
    private final List<Long> founderFrames = List.of(seconds(22), seconds(24), seconds(26));
    private final long tibiaFrame = seconds(30);
    private final long tibiaPlayFrame = seconds(30.65);   // 3.7 s video centred in its 5 s slot
    private final long gtaFrame = seconds(35);
    private final long gtaPlayFrame = seconds(36.5);      // starts moving after the pause in the speech

    private final long fadeInFrames = 15;
    private final long gradualFadeInFrames = seconds(2);

    // Kaspersky founders in a row under the logo, left to right
    private final List<String> founderPictures = List.of("YevhenKaspersky.jpg", "NatalyaKaspersky.jpg", "De-Monderik.jpg");
    private final float founderSize = 250;
    private final float founderGap = 15;
    private final float foundersBottom = 170;

    private final VideoClip windows98, tibia, gta;
    private final FittedPicture yahooMail, postgresql, kaspersky;
    private final List<FittedPicture> founders = new ArrayList<>();

    protected Year1997(long startingFrame) {
        super("anniversaries27/1997.mp3", startingFrame);
        this.endFrame = startingFrame + 2490; // 1997.mp3 lasts 41.496 s at 60 fps

        // 640x360 videos fitted to the canvas width: 797x448, centred vertically
        windows98 = new VideoClip(videosPath + "win98.mp4", 30, 104, 160, 797, 448, false);
        tibia = new VideoClip(videosPath + "tibia.mp4", 30, 104, 160, 797, 448, false);
        gta = new VideoClip(videosPath + "gta1.mp4", 30, 104, 160, 797, 448, false);

        yahooMail = new FittedPicture(picturesPath + "yahoo-mail.png", canvas, Color.WHITE);
        postgresql = new FittedPicture(picturesPath + "postgresql.png", canvas, Color.WHITE);
        kaspersky = new FittedPicture(picturesPath + "kaspersky.png", kasperskyArea, Color.WHITE);

        float rowWidth = founderPictures.size() * founderSize + (founderPictures.size() - 1) * founderGap;
        float rowLeft = canvas.x + (canvas.width - rowWidth) / 2;
        for (int i = 0; i < founderPictures.size(); i++) {
            var area = new Rectangle(rowLeft + i * (founderSize + founderGap), foundersBottom, founderSize, founderSize);
            founders.add(new FittedPicture(picturesPath + founderPictures.get(i), area, Color.WHITE));
        }
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
        long relFrame = frame - this.startingFrame;

        if (relFrame < yahooFrame) {
            drawVideo(batch, windows98, relFrame, 0);
        } else if (relFrame < postgresqlFrame) {
            drawOnWhite(batch, yahooMail, fadeIn(relFrame, yahooFrame, fadeInFrames));
        } else if (relFrame < kasperskyFrame) {
            drawOnWhite(batch, postgresql, fadeIn(relFrame, postgresqlFrame, gradualFadeInFrames));
        } else if (relFrame < tibiaFrame) {
            drawOnWhite(batch, kaspersky, fadeIn(relFrame, kasperskyFrame, fadeInFrames));
            drawFounders(batch, relFrame);
        } else if (relFrame < gtaFrame) {
            drawVideo(batch, tibia, relFrame, tibiaPlayFrame);
        } else {
            drawVideo(batch, gta, relFrame, gtaPlayFrame);
        }

        log(relFrame);
        writeYear(batch);
    }

    /** Before playFrame the video's first frame stands still; after its end the last frame stays. */
    private void drawVideo(SpriteBatch batch, VideoClip video, long relFrame, long playFrame) {
        batch.begin();
        if (relFrame < playFrame)
            video.drawFirstFrame(batch);
        else
            video.draw(batch);
        batch.end();
    }

    /** Each founder joins the row at its own time. */
    private void drawFounders(SpriteBatch batch, long relFrame) {
        batch.begin();
        for (int i = 0; i < founders.size(); i++) {
            long appearFrame = founderFrames.get(i);
            if (relFrame >= appearFrame)
                founders.get(i).drawWhole(batch, fadeIn(relFrame, appearFrame, fadeInFrames));
        }
        batch.end();
    }

    private void drawOnWhite(SpriteBatch batch, FittedPicture picture, float alpha) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(canvas.x, canvas.y, canvas.width, canvas.height);
        shapeRenderer.end();

        batch.begin();
        picture.drawWhole(batch, alpha);
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame, long durationFrames) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) durationFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("Win98 ");
        } else if (relFrame == yahooFrame) {
            System.out.print("Yahoo ");
        } else if (relFrame == postgresqlFrame) {
            System.out.print("PostgreSQL ");
        } else if (relFrame == kasperskyFrame) {
            System.out.print("Kaspersky ");
        } else if (relFrame == tibiaFrame) {
            System.out.print("Tibia ");
        } else if (relFrame == gtaFrame) {
            System.out.println("GTA ");
        }
    }
}

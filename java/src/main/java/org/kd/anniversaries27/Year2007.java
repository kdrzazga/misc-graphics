package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import org.kd.common.C64Helper;
import org.kd.common.VideoClip;

import java.util.List;

public final class Year2007 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final String picturesPath = "anniversaries27/pics/2007/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 2007.mp3.
    private final long firstKeynoteLineFrame = seconds(0);
    private final long vistaFrame = seconds(6);
    private final long kindleFrame = seconds(8);
    private final long dynamoFrame = seconds(11);
    private final long bluemixFrame = seconds(19);
    private final long witcherFrame = seconds(26);

    private final long fadeInFrames = 15;
    private final long nextLineFrames = seconds(1);

    private final List<String> keynoteLines = List.of(
            "These are not 3 separate devices", "This is 1 device.", "And we are calling it....");
    private final float keynoteFirstLineY = 640;
    private final float keynoteLineSpacing = 44;
    private final float shadowOffset = 2;

    private final VideoClip jobsKeynote;
    private final BitmapFont keynoteFont;
    private final FittedPicture vista, kindle, dynamo, bluemix, witcher;

    Year2007(long startingFrame) {
        super("anniversaries27/2007.mp3", startingFrame);
        this.endFrame = startingFrame + 1982; // 2007.mp3 lasts 33.024 s at 60 fps

        // 640x352 video fitted to the canvas width: 797x438, centred vertically
        jobsKeynote = new VideoClip("anniversaries27/video/Jobs.mp4", 30, 104, 164, 797, 438, false);
        keynoteFont = C64Helper.createFont(34, "Helvetica Regular.otf");

        vista = new FittedPicture(picturesPath + "vista.jpg", canvas, Color.BLACK, true);
        kindle = new FittedPicture(picturesPath + "Kindle.png", canvas, Color.WHITE);
        dynamo = new FittedPicture(picturesPath + "dynamo.jpg", canvas, new Color(0.93f, 0.94f, 0.99f, 1));
        bluemix = new FittedPicture(picturesPath + "bluemix.jpg", canvas, new Color(0.09f, 0.145f, 0.19f, 1));
        witcher = new FittedPicture(picturesPath + "wiesiek.jpg", canvas, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
        long relFrame = frame - this.startingFrame;

        if (relFrame < vistaFrame)
            drawKeynote(batch, relFrame);
        else if (relFrame < kindleFrame)
            drawWhole(batch, vista, relFrame, vistaFrame);
        else if (relFrame < dynamoFrame)
            drawWhole(batch, kindle, relFrame, kindleFrame);
        else if (relFrame < bluemixFrame)
            drawWhole(batch, dynamo, relFrame, dynamoFrame);
        else if (relFrame < witcherFrame)
            drawWhole(batch, bluemix, relFrame, bluemixFrame);
        else
            drawWhole(batch, witcher, relFrame, witcherFrame);

        log(relFrame);
        writeYear(batch);
    }

    /** Muted iPhone keynote with the famous lines added one per second over it. */
    private void drawKeynote(SpriteBatch batch, long relFrame) {
        batch.begin();
        jobsKeynote.draw(batch);
        for (int i = 0; i < keynoteLines.size(); i++) {
            long appearFrame = firstKeynoteLineFrame + i * nextLineFrames;
            if (relFrame >= appearFrame)
                drawKeynoteLine(batch, keynoteLines.get(i), keynoteFirstLineY - i * keynoteLineSpacing,
                        fadeIn(relFrame, appearFrame));
        }
        batch.end();
    }

    private void drawKeynoteLine(SpriteBatch batch, String line, float y, float alpha) {
        keynoteFont.setColor(0, 0, 0, alpha);
        keynoteFont.draw(batch, line, canvas.x + shadowOffset, y - shadowOffset, canvas.width, Align.center, false);
        keynoteFont.setColor(1, 1, 1, alpha);
        keynoteFont.draw(batch, line, canvas.x, y, canvas.width, Align.center, false);
    }

    private void drawWhole(SpriteBatch batch, FittedPicture picture, long relFrame, long appearFrame) {
        picture.fillArea(shapeRenderer);
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, appearFrame));
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) fadeInFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("IPhone ");
        } else if (relFrame == vistaFrame) {
            System.out.print("WinVista ");
        } else if (relFrame == kindleFrame) {
            System.out.print("Kindle ");
        } else if (relFrame == dynamoFrame) {
            System.out.print("Amazon Dynamo ");
        } else if (relFrame == bluemixFrame) {
            System.out.print("IBM Cloud ");
        } else if (relFrame == witcherFrame)
            System.out.println("Witcher");
    }
}

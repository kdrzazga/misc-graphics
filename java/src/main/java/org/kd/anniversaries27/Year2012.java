package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.kd.anniversaries27.FittedPicture.Part;

import java.util.List;

public final class Year2012 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle canvasLeft = new Rectangle(104, 112, 300, 543);
    private final Rectangle canvasRight = new Rectangle(404, 112, 497, 543);
    private final String picturesPath = "anniversaries27/pics/2012/";

    // Stage start frames, relative to the start of the year. Most match a pause in 2012.mp3.
    private final long facebookFrame = seconds(0);
    private final long firstStashFrame = seconds(4);
    private final long iphoneFrame = seconds(10);
    private final long mountainLionFrame = seconds(14);
    private final long windows8Frame = seconds(24);
    private final long windows8MenuFrame = seconds(28);
    private final long typeScriptFrame = seconds(32);

    private final long gradualFadeInFrames = seconds(4);
    private final long quickFadeInFrames = 15;
    private final long nextPartFrames = seconds(1);

    // Picture parts in picture pixels, origin top-left
    private final Part facebookCaption = new Part(0, 0, 600, 130);
    private final List<Part> ipoStashes = List.of(
            new Part(0, 130, 228, 208), new Part(228, 130, 158, 208), new Part(386, 130, 214, 208));

    private final Color white = new Color(1f, 1f, 1f, 1);
    private final FittedPicture facebookIpo, iphone5, mountainLion, windows8, windows8Menu, typeScript;

    Year2012(long startingFrame) {
        super("anniversaries27/2012.mp3", startingFrame);
        this.endFrame = startingFrame + 2153; // 2012.mp3 lasts 35.880 s at 60 fps

        facebookIpo = new FittedPicture(picturesPath + "fb-ipo.jpg", canvas, new Color(0.64f, 0.64f, 0.64f, 1), true);
        iphone5 = new FittedPicture(picturesPath + "inphone5.png", canvasLeft, white);
        mountainLion = new FittedPicture(picturesPath + "osx-ml.png", canvasRight, white);
        windows8 = new FittedPicture(picturesPath + "win8.png", canvas, Color.BLACK, true);
        windows8Menu = new FittedPicture(picturesPath + "win8menu.png", canvas, Color.BLACK, true);
        typeScript = new FittedPicture(picturesPath + "ts.png", canvas, new Color(0.19f, 0.47f, 0.78f, 1));
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
        long relFrame = frame - this.startingFrame;

        if (relFrame < iphoneFrame)
            drawFacebookIpo(batch, relFrame);
        else if (relFrame < windows8Frame)
            drawAppleDevices(batch, relFrame);
        else if (relFrame < windows8MenuFrame)
            drawWhole(batch, windows8, relFrame, windows8Frame);
        else if (relFrame < typeScriptFrame)
            drawWhole(batch, windows8Menu, relFrame, windows8MenuFrame);
        else
            drawWhole(batch, typeScript, relFrame, typeScriptFrame);

        log(relFrame);
        writeYear(batch);
    }

    /** The facebook caption appears gradually, then the I, P and O stashes one per second. */
    private void drawFacebookIpo(SpriteBatch batch, long relFrame) {
        facebookIpo.fillArea(shapeRenderer);
        batch.begin();
        facebookIpo.drawPart(batch, facebookCaption, fadeIn(relFrame, facebookFrame, gradualFadeInFrames));
        for (int i = 0; i < ipoStashes.size(); i++) {
            long appearFrame = firstStashFrame + i * nextPartFrames;
            if (relFrame >= appearFrame)
                facebookIpo.drawPart(batch, ipoStashes.get(i), fadeIn(relFrame, appearFrame, quickFadeInFrames));
        }
        batch.end();
    }

    /** iPhone 5 appears gradually on the left, OS X Mountain Lion joins it on the right. */
    private void drawAppleDevices(SpriteBatch batch, long relFrame) {
        fillCanvas(white);
        batch.begin();
        iphone5.drawWhole(batch, fadeIn(relFrame, iphoneFrame, gradualFadeInFrames));
        if (relFrame >= mountainLionFrame)
            mountainLion.drawWhole(batch, fadeIn(relFrame, mountainLionFrame, nextPartFrames));
        batch.end();
    }

    private void drawWhole(SpriteBatch batch, FittedPicture picture, long relFrame, long appearFrame) {
        picture.fillArea(shapeRenderer);
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, appearFrame, quickFadeInFrames));
        batch.end();
    }

    private void fillCanvas(Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(canvas.x, canvas.y, canvas.width, canvas.height);
        shapeRenderer.end();
    }

    private float fadeIn(long relFrame, long appearFrame, long durationFrames) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) durationFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("FB ");
        } else if (relFrame == iphoneFrame) {
            System.out.print("iPhone5 ");
        } else if (relFrame == mountainLionFrame) {
            System.out.print("Apple OSX ");
        } else if (relFrame == windows8Frame) {
            System.out.print("Win8 ");
        } else if (relFrame == typeScriptFrame)
            System.out.println("TS");
    }
}

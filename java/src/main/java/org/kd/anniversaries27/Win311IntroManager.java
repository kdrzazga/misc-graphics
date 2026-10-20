package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import org.kd.common.DrawnMousePointer;
import org.kd.common.Helper;
import org.kd.common.Letterbox;
import org.kd.common.Scene;
import org.kd.common.VirtualScreenGraphics;
import org.kd.common.VirtualScreenInput;
import org.kd.win311.Win311Manager;

public class Win311IntroManager extends Win311Manager {

    private final long outroDurationFrames = 1320; // outro used to end at frame 38000, 1320 frames after 1952
    private final int demoWidth, demoHeight;

    private Scene5YearBrowser yearBrowser;
    private Graphics screenGraphics;
    private FrameBuffer demoFrame;
    private SpriteBatch screenBatch;
    private DrawnMousePointer drawnPointer; // full screen only

    /** The demo is drawn at demoWidth x demoHeight and scaled to the screen, keeping its proportions. */
    public Win311IntroManager(int demoWidth, int demoHeight) {
        super("win311/EscapeRamos.mp3");
        this.demoWidth = demoWidth;
        this.demoHeight = demoHeight;
    }

    @Override
    public void create() {
        screenGraphics = Gdx.graphics;
        Gdx.graphics = new VirtualScreenGraphics(screenGraphics, demoWidth, demoHeight);
        Gdx.input = new VirtualScreenInput(Gdx.input, screenGraphics, demoWidth, demoHeight);

        demoFrame = new FrameBuffer(Pixmap.Format.RGB888, demoWidth, demoHeight, false);
        demoFrame.getColorBufferTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        screenBatch = new SpriteBatch();
        if (screenGraphics.isFullscreen())
            drawnPointer = new DrawnMousePointer();

        super.create(new Scene4Paint27());
    }

    @Override
    public void render() {
        demoFrame.begin();
        renderDemoFrame();
        drawPointer();
        demoFrame.end(0, 0, screenGraphics.getBackBufferWidth(), screenGraphics.getBackBufferHeight());

        drawDemoFrameOnScreen();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    private void renderDemoFrame() {
        super.render();

        //Paintbrush loaded
        long frame = Gdx.graphics.getFrameId();

        if (frame == Scene5YearBrowser.START_FRAME) {
            yearBrowser = new Scene5YearBrowser();
            runScene(yearBrowser, 0.3f);
        } else if (yearBrowser != null && frame == yearBrowser.getEndFrame()) {
            System.out.println(Helper.countElapsedTime() + " OUTRO scene started [frame " + frame + "]");
            var scene6 = new Scene6Outro(outroDurationFrames);
            runScene(scene6, 1f);
        }
    }

    /** In full screen the pointer is part of the demo frame, so it is scaled together with the picture. */
    private void drawPointer() {
        if (drawnPointer == null)
            return;
        screenBatch.begin();
        drawnPointer.draw(screenBatch, Gdx.input.getX(), Gdx.input.getY(), demoHeight);
        screenBatch.end();
    }

    /** Scales the demo frame to the largest centred rectangle with the demo's proportions; the rest stays black. */
    private void drawDemoFrameOnScreen() {
        var letterbox = Letterbox.fit(demoWidth, demoHeight,
                screenGraphics.getBackBufferWidth(), screenGraphics.getBackBufferHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(letterbox.x(), letterbox.y(), letterbox.width(), letterbox.height());

        // screenBatch's projection covers demoWidth x demoHeight; the viewport scales it. Frame buffers are upside down.
        screenBatch.begin();
        screenBatch.draw(demoFrame.getColorBufferTexture(), 0, 0, demoWidth, demoHeight,
                0, 0, demoWidth, demoHeight, false, true);
        screenBatch.end();
    }

    private void runScene(Scene scene, float volume) {
        scene.create();
        sceneManager.addScene(scene.id, scene);
        sceneManager.switchScene(scene.id);
        this.tune.setVolume(volume);
    }

    @Override
    public void dispose() {
        super.dispose();
        demoFrame.dispose();
        screenBatch.dispose();
        if (drawnPointer != null)
            drawnPointer.dispose();
    }
}

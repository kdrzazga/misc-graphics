package org.kd.common;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;

/**
 * Graphics that reports a fixed virtual screen size and delegates everything else to the real backend.
 * Installed as Gdx.graphics, it makes code written for a fixed resolution (batches and shape renderers
 * take their projection from getWidth/getHeight) keep working when the real screen is bigger,
 * e.g. in full screen. The demo is then rendered into a frame buffer of the virtual size and scaled to the screen.
 */
public class VirtualScreenGraphics implements Graphics {

    private final Graphics real;
    private final int virtualWidth, virtualHeight;

    public VirtualScreenGraphics(Graphics real, int virtualWidth, int virtualHeight) {
        this.real = real;
        this.virtualWidth = virtualWidth;
        this.virtualHeight = virtualHeight;
    }

    @Override public int getWidth() { return virtualWidth; }
    @Override public int getHeight() { return virtualHeight; }
    @Override public int getBackBufferWidth() { return virtualWidth; }
    @Override public int getBackBufferHeight() { return virtualHeight; }
    @Override public float getBackBufferScale() { return 1f; }

    @Override public boolean isGL30Available() { return real.isGL30Available(); }
    @Override public GL20 getGL20() { return real.getGL20(); }
    @Override public GL30 getGL30() { return real.getGL30(); }
    @Override public void setGL20(GL20 gl20) { real.setGL20(gl20); }
    @Override public void setGL30(GL30 gl30) { real.setGL30(gl30); }
    @Override public int getSafeInsetLeft() { return real.getSafeInsetLeft(); }
    @Override public int getSafeInsetTop() { return real.getSafeInsetTop(); }
    @Override public int getSafeInsetBottom() { return real.getSafeInsetBottom(); }
    @Override public int getSafeInsetRight() { return real.getSafeInsetRight(); }
    @Override public long getFrameId() { return real.getFrameId(); }
    @Override public float getDeltaTime() { return real.getDeltaTime(); }
    @Override public float getRawDeltaTime() { return real.getRawDeltaTime(); }
    @Override public int getFramesPerSecond() { return real.getFramesPerSecond(); }
    @Override public GraphicsType getType() { return real.getType(); }
    @Override public GLVersion getGLVersion() { return real.getGLVersion(); }
    @Override public float getPpiX() { return real.getPpiX(); }
    @Override public float getPpiY() { return real.getPpiY(); }
    @Override public float getPpcX() { return real.getPpcX(); }
    @Override public float getPpcY() { return real.getPpcY(); }
    @Override public float getDensity() { return real.getDensity(); }
    @Override public boolean supportsDisplayModeChange() { return real.supportsDisplayModeChange(); }
    @Override public Monitor getPrimaryMonitor() { return real.getPrimaryMonitor(); }
    @Override public Monitor getMonitor() { return real.getMonitor(); }
    @Override public Monitor[] getMonitors() { return real.getMonitors(); }
    @Override public DisplayMode[] getDisplayModes() { return real.getDisplayModes(); }
    @Override public DisplayMode[] getDisplayModes(Monitor monitor) { return real.getDisplayModes(monitor); }
    @Override public DisplayMode getDisplayMode() { return real.getDisplayMode(); }
    @Override public DisplayMode getDisplayMode(Monitor monitor) { return real.getDisplayMode(monitor); }
    @Override public boolean setFullscreenMode(DisplayMode displayMode) { return real.setFullscreenMode(displayMode); }
    @Override public boolean setWindowedMode(int width, int height) { return real.setWindowedMode(width, height); }
    @Override public void setTitle(String title) { real.setTitle(title); }
    @Override public void setUndecorated(boolean undecorated) { real.setUndecorated(undecorated); }
    @Override public void setResizable(boolean resizable) { real.setResizable(resizable); }
    @Override public void setVSync(boolean vsync) { real.setVSync(vsync); }
    @Override public void setForegroundFPS(int fps) { real.setForegroundFPS(fps); }
    @Override public BufferFormat getBufferFormat() { return real.getBufferFormat(); }
    @Override public boolean supportsExtension(String extension) { return real.supportsExtension(extension); }
    @Override public void setContinuousRendering(boolean isContinuous) { real.setContinuousRendering(isContinuous); }
    @Override public boolean isContinuousRendering() { return real.isContinuousRendering(); }
    @Override public void requestRendering() { real.requestRendering(); }
    @Override public boolean isFullscreen() { return real.isFullscreen(); }
    @Override public Cursor newCursor(Pixmap pixmap, int xHotspot, int yHotspot) { return real.newCursor(pixmap, xHotspot, yHotspot); }
    @Override public void setCursor(Cursor cursor) { real.setCursor(cursor); }
    @Override public void setSystemCursor(Cursor.SystemCursor systemCursor) { real.setSystemCursor(systemCursor); }
}

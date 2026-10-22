package org.kd.common;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import org.lwjgl.input.Mouse;

/**
 * Input that works in the coordinates of a fixed virtual screen (origin top-left, as Gdx.input uses)
 * while the demo is scaled into a letterbox on the real screen. Moving and reading the mouse pointer
 * is translated between the two; everything else is delegated to the real backend.
 * Pairs with {@link VirtualScreenGraphics}.
 */
public class VirtualScreenInput implements Input {

    private final Input real;
    private final Graphics screenGraphics;
    private final int virtualWidth, virtualHeight;

    public VirtualScreenInput(Input real, Graphics screenGraphics, int virtualWidth, int virtualHeight) {
        this.real = real;
        this.screenGraphics = screenGraphics;
        this.virtualWidth = virtualWidth;
        this.virtualHeight = virtualHeight;
    }

    /** LWJGL mouse coordinates are screen pixels with the origin bottom-left. */
    @Override
    public void setCursorPosition(int x, int y) {
        var letterbox = letterbox();
        int screenX = letterbox.x() + Math.round(x * letterbox.scale());
        int screenY = letterbox.y() + Math.round((virtualHeight - 1 - y) * letterbox.scale());
        Mouse.setCursorPosition(screenX, screenY);
    }

    @Override
    public int getX() {
        var letterbox = letterbox();
        return Math.round((Mouse.getX() - letterbox.x()) / letterbox.scale());
    }

    @Override
    public int getY() {
        var letterbox = letterbox();
        return virtualHeight - 1 - Math.round((Mouse.getY() - letterbox.y()) / letterbox.scale());
    }

    @Override public int getX(int pointer) { return pointer == 0 ? getX() : 0; }
    @Override public int getY(int pointer) { return pointer == 0 ? getY() : 0; }

    private Letterbox letterbox() {
        return Letterbox.fit(virtualWidth, virtualHeight,
                screenGraphics.getBackBufferWidth(), screenGraphics.getBackBufferHeight());
    }

    @Override public float getAccelerometerX() { return real.getAccelerometerX(); }
    @Override public float getAccelerometerY() { return real.getAccelerometerY(); }
    @Override public float getAccelerometerZ() { return real.getAccelerometerZ(); }
    @Override public float getGyroscopeX() { return real.getGyroscopeX(); }
    @Override public float getGyroscopeY() { return real.getGyroscopeY(); }
    @Override public float getGyroscopeZ() { return real.getGyroscopeZ(); }
    @Override public int getMaxPointers() { return real.getMaxPointers(); }
    @Override public int getDeltaX() { return real.getDeltaX(); }
    @Override public int getDeltaX(int pointer) { return real.getDeltaX(pointer); }
    @Override public int getDeltaY() { return real.getDeltaY(); }
    @Override public int getDeltaY(int pointer) { return real.getDeltaY(pointer); }
    @Override public boolean isTouched() { return real.isTouched(); }
    @Override public boolean justTouched() { return real.justTouched(); }
    @Override public boolean isTouched(int pointer) { return real.isTouched(pointer); }
    @Override public float getPressure() { return real.getPressure(); }
    @Override public float getPressure(int pointer) { return real.getPressure(pointer); }
    @Override public boolean isButtonPressed(int button) { return real.isButtonPressed(button); }
    @Override public boolean isButtonJustPressed(int button) { return real.isButtonJustPressed(button); }
    @Override public boolean isKeyPressed(int key) { return real.isKeyPressed(key); }
    @Override public boolean isKeyJustPressed(int key) { return real.isKeyJustPressed(key); }
    @Override public void getTextInput(TextInputListener listener, String title, String text, String hint) { real.getTextInput(listener, title, text, hint); }
    @Override public void getTextInput(TextInputListener listener, String title, String text, String hint, OnscreenKeyboardType type) { real.getTextInput(listener, title, text, hint, type); }
    @Override public void setOnscreenKeyboardVisible(boolean visible) { real.setOnscreenKeyboardVisible(visible); }
    @Override public void setOnscreenKeyboardVisible(boolean visible, OnscreenKeyboardType type) { real.setOnscreenKeyboardVisible(visible, type); }
    @Override public void vibrate(int milliseconds) { real.vibrate(milliseconds); }
    @Override public void vibrate(long[] pattern, int repeat) { real.vibrate(pattern, repeat); }
    @Override public void cancelVibrate() { real.cancelVibrate(); }
    @Override public float getAzimuth() { return real.getAzimuth(); }
    @Override public float getPitch() { return real.getPitch(); }
    @Override public float getRoll() { return real.getRoll(); }
    @Override public void getRotationMatrix(float[] matrix) { real.getRotationMatrix(matrix); }
    @Override public long getCurrentEventTime() { return real.getCurrentEventTime(); }
    @Override public void setCatchBackKey(boolean catchBack) { real.setCatchBackKey(catchBack); }
    @Override public boolean isCatchBackKey() { return real.isCatchBackKey(); }
    @Override public void setCatchMenuKey(boolean catchMenu) { real.setCatchMenuKey(catchMenu); }
    @Override public boolean isCatchMenuKey() { return real.isCatchMenuKey(); }
    @Override public void setCatchKey(int keycode, boolean catchKey) { real.setCatchKey(keycode, catchKey); }
    @Override public boolean isCatchKey(int keycode) { return real.isCatchKey(keycode); }
    @Override public void setInputProcessor(InputProcessor processor) { real.setInputProcessor(processor); }
    @Override public InputProcessor getInputProcessor() { return real.getInputProcessor(); }
    @Override public boolean isPeripheralAvailable(Peripheral peripheral) { return real.isPeripheralAvailable(peripheral); }
    @Override public int getRotation() { return real.getRotation(); }
    @Override public Orientation getNativeOrientation() { return real.getNativeOrientation(); }
    @Override public void setCursorCatched(boolean catched) { real.setCursorCatched(catched); }
    @Override public boolean isCursorCatched() { return real.isCursorCatched(); }
}

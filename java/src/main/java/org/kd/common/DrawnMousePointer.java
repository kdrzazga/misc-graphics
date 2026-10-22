package org.kd.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

/**
 * Classic Windows arrow drawn by the demo itself, for full screen where the system pointer
 * is not reliably shown and would not follow the demo's scaling anyway.
 * The system pointer is replaced by an invisible one, so the two are never seen together.
 */
public class DrawnMousePointer {

    // X = black outline, o = white fill, space = transparent. Hot spot is the top-left pixel.
    private final String[] arrow = {
            "X",
            "XX",
            "XoX",
            "XooX",
            "XoooX",
            "XooooX",
            "XoooooX",
            "XooooooX",
            "XoooooooX",
            "XooooooooX",
            "XoooooooooX",
            "XooooooXXXXX",
            "XoooXooX",
            "XooXXooX",
            "XoX  XooX",
            "XX   XooX",
            "X     XooX",
            "      XooX",
            "       XX",
    };

    private final Texture texture;
    private final Cursor invisibleSystemPointer;

    public DrawnMousePointer() {
        texture = new Texture(createArrowPixmap());
        try {
            invisibleSystemPointer = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
        } catch (LWJGLException e) {
            throw new IllegalStateException("Cannot create an invisible system pointer", e);
        }
    }

    /** x, y: pointer position with the origin top-left, as Gdx.input reports it; screenHeight of the same coordinates. */
    public void draw(SpriteBatch batch, int x, int y, int screenHeight) {
        hideSystemPointer();
        batch.draw(texture, x, screenHeight - y - texture.getHeight());
    }

    /** Called every frame, as switching the system cursor shape (e.g. in Program Manager) brings the default one back. */
    private void hideSystemPointer() {
        if (Mouse.getNativeCursor() == invisibleSystemPointer)
            return;
        try {
            Mouse.setNativeCursor(invisibleSystemPointer);
        } catch (LWJGLException e) {
            throw new IllegalStateException("Cannot hide the system pointer", e);
        }
    }

    private Pixmap createArrowPixmap() {
        int width = 0;
        for (String row : arrow)
            width = Math.max(width, row.length());

        var pixmap = new Pixmap(width, arrow.length, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        for (int y = 0; y < arrow.length; y++) {
            for (int x = 0; x < arrow[y].length(); x++) {
                char pixel = arrow[y].charAt(x);
                if (pixel == ' ')
                    continue;
                pixmap.setColor(pixel == 'X' ? Color.BLACK : Color.WHITE);
                pixmap.drawPixel(x, y);
            }
        }
        return pixmap;
    }

    public void dispose() {
        texture.dispose();
        invisibleSystemPointer.destroy();
    }
}

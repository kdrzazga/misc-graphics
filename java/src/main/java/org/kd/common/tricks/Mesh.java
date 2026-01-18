package org.kd.common.tricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class Mesh {
    public static Pixmap createMeshPixmap(Color color, int squareSize, int horizontalSquares, int verticalSquares) {
        int textureWidth = Math.round((0f + horizontalSquares) * squareSize);
        int textureHeight = Math.round((0f + verticalSquares) * squareSize);
        var pixmap = new Pixmap(textureWidth, textureHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(color);

        for (int xx = 0; xx < horizontalSquares; xx++)
            for (int yy = 0; yy <= horizontalSquares; yy++) {

                int x = (textureWidth - squareSize) / 2 + xx * squareSize;
                int y = (textureHeight - squareSize) / 2 + (yy-1) * squareSize;
                pixmap.drawRectangle(x, y, squareSize, squareSize);
            }
        return pixmap;
    }
}

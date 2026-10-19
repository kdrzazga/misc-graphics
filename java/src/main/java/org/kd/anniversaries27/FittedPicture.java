package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Picture scaled into an area (keeping proportions, centred) that can be drawn whole or part by part.
 * It either fits inside the area, or covers the whole area with the overflowing edges cropped.
 * Parts are given in picture pixels with the origin in the top-left corner, as in an image editor.
 */
final class FittedPicture {

    record Part(int x, int y, int width, int height) {
    }

    private final Texture texture;
    private final Rectangle area;
    private final Color background;
    private final float scale, left, top;
    // Part of the picture that lies inside the area, in picture pixels
    private final float visibleLeft, visibleRight, visibleTop, visibleBottom;

    /** Fits the picture inside the area. */
    FittedPicture(String path, Rectangle area, Color background) {
        this(path, area, background, false);
    }

    /** With coverArea the picture fills the whole area and its overflowing edges are cropped. */
    FittedPicture(String path, Rectangle area, Color background, boolean coverArea) {
        this.texture = new Texture(path);
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.area = area;
        this.background = background;
        float widthRatio = area.width / texture.getWidth();
        float heightRatio = area.height / texture.getHeight();
        this.scale = coverArea ? Math.max(widthRatio, heightRatio) : Math.min(widthRatio, heightRatio);
        this.left = area.x + (area.width - texture.getWidth() * scale) / 2;
        this.top = area.y + (area.height + texture.getHeight() * scale) / 2;
        this.visibleLeft = (area.x - left) / scale;
        this.visibleRight = (area.x + area.width - left) / scale;
        this.visibleTop = (top - area.y - area.height) / scale;
        this.visibleBottom = (top - area.y) / scale;
    }

    /** Fills the whole area with the picture's own background colour, so the picture has no visible edges. */
    void fillArea(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(background);
        shapeRenderer.rect(area.x, area.y, area.width, area.height);
        shapeRenderer.end();
    }

    void drawWhole(SpriteBatch batch, float alpha) {
        drawPart(batch, 0, 0, texture.getWidth(), texture.getHeight(), alpha);
    }

    void drawPart(SpriteBatch batch, Part part, float alpha) {
        drawPart(batch, part.x(), part.y(), part.width(), part.height(), alpha);
    }

    void drawPart(SpriteBatch batch, float x, float y, float width, float height, float alpha) {
        float partLeft = Math.max(x, visibleLeft);
        float partRight = Math.min(x + width, visibleRight);
        float partTop = Math.max(y, visibleTop);
        float partBottom = Math.min(y + height, visibleBottom);
        if (partLeft >= partRight || partTop >= partBottom)
            return;

        float textureWidth = texture.getWidth();
        float textureHeight = texture.getHeight();
        batch.setColor(1, 1, 1, alpha);
        // u/v of the bottom-left corner first, then of the top-right corner
        batch.draw(texture, left + partLeft * scale, top - partBottom * scale,
                (partRight - partLeft) * scale, (partBottom - partTop) * scale,
                partLeft / textureWidth, partBottom / textureHeight,
                partRight / textureWidth, partTop / textureHeight);
        batch.setColor(Color.WHITE);
    }
}

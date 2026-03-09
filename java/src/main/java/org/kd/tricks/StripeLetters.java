package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StripeLetters {

    private final int width, height;
    private final Sprite caption;
    private int shift;
    private long firstFrame;

    public StripeLetters(String captionPath, int x, int y) {
        var captionTexture = new Texture(captionPath);

        width = captionTexture.getWidth();
        height = captionTexture.getHeight();

        caption = new Sprite(captionTexture);
        caption.setPosition(x, y);

        firstFrame = Gdx.graphics.getFrameId();
    }

    public void draw(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        var frame = Gdx.graphics.getFrameId() - firstFrame;
        shift = Long.valueOf(Math.round((height) * Math.abs(Math.sin(frame / 300)))).intValue();

        for (int i = 0; i < height; i++) {
            float t = (float) (i + shift) / height;
            Color color = hsvToRgb(t * 360, 1f, 1f);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(caption.getX(), caption.getY() + i, caption.getWidth(), 1);
        }

        shapeRenderer.end();

        batch.begin();
        caption.draw(batch);
        batch.end();
    }

    private Color hsvToRgb(float hue, float saturation, float value) {
        float c = value * saturation;
        float x = c * (1 - Math.abs((hue / 60) % 2 - 1));
        float m = value - c;

        float r = 0, g = 0, b = 0;

        if (hue < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (hue < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (hue < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (hue < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (hue < 300) {
            r = x;
            g = 0;
            b = c;
        } else {
            r = c;
            g = 0;
            b = x;
        }

        return new Color(r + m, g + m, b + m, 1f);
    }
}

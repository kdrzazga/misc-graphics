package org.kd.tricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StripeLetters {

    private final int width, height;
    private final Sprite caption;

    public StripeLetters(String captionPath, int x, int y) {
        var captionTexture = new Texture(captionPath);

        width = captionTexture.getWidth();
        height = captionTexture.getHeight();

        caption = new Sprite(captionTexture);
        caption.setPosition(x, y);

    }

    public void draw(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(caption.getX(), caption.getY(), caption.getWidth(), caption.getHeight());
        shapeRenderer.end();

        batch.begin();
        caption.draw(batch);
        batch.end();
    }

}

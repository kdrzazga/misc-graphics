package pl.kaplus.scissors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TravellingLogo {
    Sprite sprite;
    private float spriteSpeed = 100f; // pixels per second

    public TravellingLogo(Texture texture, float startX, float startY, float width, float height) {
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setPosition(startX, startY);
    }

    public void move(float deltaTime) {
        float newX = sprite.getX() - spriteSpeed * deltaTime;
        sprite.setX(newX);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getWidth() {
        return sprite.getWidth();
    }
}
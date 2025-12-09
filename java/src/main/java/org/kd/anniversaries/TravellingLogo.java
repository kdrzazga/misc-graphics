package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Colors;

final class TravellingLogo {
    public Sprite sprite;
    public float speed;
    private float x, y, minX, maxX;
    private Direction direction;

    TravellingLogo(float y, float minX, float maxX, String imgPath) {
        this.direction = Direction.LEFT;

        this.x = maxX;
        this.minX = minX;
        this.maxX = maxX;
        this.speed = 2f;

        this.sprite = new Sprite(new Texture(imgPath));
    }

    public void move() {
        if (this.direction == Direction.LEFT) {
            if (this.x > minX + this.speed) {
                this.x -= this.speed;
            } else
                this.direction = Direction.RIGHT;
        }
        else {
            if (this.x < maxX - this.speed) {
                this.x += this.speed;
            } else
                this.direction = Direction.LEFT;
        }

    }

    public void scale(float newScale){
        this.sprite.setScale(newScale);
    }

    public void colorize(C64Colors color){
        this.sprite.setColor(C64Colors.LIGHT_BLUE.toBadlogicColor());
    }

    public void draw(SpriteBatch batch) {
        this.sprite.draw(batch);
    }

    enum Direction {
        LEFT, RIGHT
    }
}

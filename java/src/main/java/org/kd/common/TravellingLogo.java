package org.kd.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class TravellingLogo {
    Sprite sprite;
    public float spriteSpeed = 100f; // pixels per second

    public TravellingLogo(Texture texture, float startX, float startY, float width, float height) {
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setPosition(startX, startY);
    }

    public void move(float deltaTime, int screenWidth) {
        float newX = sprite.getX() - spriteSpeed * deltaTime;
        sprite.setX(newX);

        if (this.getX() + this.getWidth() < 0) {
            this.sprite.setX(screenWidth);
        }
    }

    public void draw(SpriteBatch batch, int screenWidth, int screenHeight) {
        var scissors = new Rectangle(0, 0, screenWidth, screenHeight);
        if (ScissorStack.pushScissors(scissors)) {
            sprite.draw(batch);
        }
        ScissorStack.popScissors();
    }

    public void colorize(C64Colors color){
        this.sprite.setColor(color.toBadlogicColor());
    }

    public void changeDirection(){
        this.spriteSpeed *= -1;
        this.sprite.setX(this.getX() - this.spriteSpeed/50);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public Sprite getSprite() {
        return sprite;
    }
}
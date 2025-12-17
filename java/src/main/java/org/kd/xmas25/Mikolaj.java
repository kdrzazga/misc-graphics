package org.kd.xmas25;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.kd.common.Globals;

public class Mikolaj extends Sprite {

    private final float speed = 2.5f;
    private float speedX = speed;

    public Mikolaj(Texture mikolajPng) {
        super(mikolajPng);

        this.setScale(0.6f);
        this.setY(Globals.DEFAULT_CURSOR_Y);
    }

    public void move() {

        if (this.getX() < 1) {
            this.speedX = speed;
            this.setFlip(false, false);
        } else if (this.getX() > Globals.SCREEN_WIDTH - 370) {
            this.speedX = -speed;
            this.setFlip(true, false);
        }

        var newX = this.getX() + this.speedX;
        this.setX(newX);
        this.setY((float) (Globals.DEFAULT_CURSOR_Y + 8 * Math.sin(newX / (8 * Math.PI))));
    }
}

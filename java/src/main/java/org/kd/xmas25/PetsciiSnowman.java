package org.kd.xmas25;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PetsciiSnowman extends Sprite {


    public PetsciiSnowman() {
        super(new Texture("dream210/snowman/PetsciiSnowman.png"));

        this.setScale(0.55f, 0f);
        this.setY(-50);
        this.setX(-10);
    }
}

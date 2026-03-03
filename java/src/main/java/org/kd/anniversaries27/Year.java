package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.anniversaries.BaseYear;
import org.kd.common.Scene;

public abstract class Year extends BaseYear {

    public Year(String statementPath, long startingFrame) {
        super(statementPath, startingFrame);
    }
    public abstract void draw(long frame, SpriteBatch batch);

}

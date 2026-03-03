package org.kd.anniversaries;

import org.kd.common.Scene;

public abstract class Year extends BaseYear {

    public Year(String statementPath, long startingFrame) {
        super(statementPath, startingFrame);
    }

    public abstract void draw(long frame, Scene scene);

}

package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.anniversaries.BaseYear;
import org.kd.common.C64Helper;

public abstract class Year extends BaseYear {

    protected ShapeRenderer shapeRenderer;

    public Year(String statementPath, long startingFrame) {
        super(statementPath, startingFrame);
        shapeRenderer = new ShapeRenderer();
        font1 = C64Helper.createFont(44, "fontstruct.ttf");
    }

    public abstract void draw(long frame, SpriteBatch batch);

    protected String getYear() {
        return this.getClass().getSimpleName().replace("Year", "");
    }

    protected void writeYear(SpriteBatch batch) {
        int topY = 131;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(104, topY-19, 88, 32);
        shapeRenderer.end();

        batch.begin();
        font1.draw(batch, getYear(), 104, topY-10);
        batch.end();
    }
}

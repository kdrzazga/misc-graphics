package org.kd.norton;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public class Scene2Norton  extends Scene {

    public final static int START_FRAME = 250;
    public final static String ID = "win-load";

    private Sprite bkg;
    private Batch batch;

    public Scene2Norton() {
        super(ID);
    }

    @Override
    public void create() {
        var bkgTexture = new Texture("nc/nc.png");
        bkg = new Sprite(bkgTexture);
        batch = new SpriteBatch();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        batch.begin();
        bkg.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}

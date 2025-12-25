package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public class Scene2WinLoad extends Scene {

    public final static int START_FRAME = 150;
    public final static String ID = "win-load";

    private Sprite logo;
    private Batch batch;

    public Scene2WinLoad() {
        super(ID);
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch(1);
        var logoTexture = new Texture(Gdx.files.internal("win311/loading.png"));
        this.logo = new Sprite(logoTexture);
        this.logo.setScale(0.1f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(this.logo, 1024 / 2 - this.logo.getWidth() / 2, 786 / 2 - this.logo.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}

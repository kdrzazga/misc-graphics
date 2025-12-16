package org.kd.xmas25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.Globals;

public class Scene2 extends BasicC64Screen {
    private boolean snowing;
    public SpriteBatch batch2;

    public Scene2(String id) {
        super(id);
        this.backgroundScreenPng = "winter/lblue-ready.png";
        this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
        this.borderColor = C64Colors.WHITE;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        var frame = Gdx.graphics.getFrameId();
        if (frame == WishesHelper.SCENE2_START_FRAME + 1) {
            Globals.cursorY = Math.round(0.753 * Globals.SCREEN_HEIGHT) - 2;
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        batch2.end();
    }
}

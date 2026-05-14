package org.kd.talkingheads;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;

public class Scene1 extends BasicC64Screen {
    private SpriteBatch batch2;

    public Scene1() {
        super("1");
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }
}

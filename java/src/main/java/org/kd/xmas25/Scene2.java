package org.kd.xmas25;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;

import java.util.List;

public class Scene2 extends BasicC64Screen {
    private boolean snowing;
    private List<Sprite> snowflakes;
    public SpriteBatch batch2;

    public Scene2(String id) {
        super(id);
    }
}

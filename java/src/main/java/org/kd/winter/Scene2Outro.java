package org.kd.winter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.C64Helper;
import org.kd.common.Globals;

public class Scene2Outro extends BasicC64Screen {
    SpriteBatch batch2;
    BitmapFont font3;
    Sprite ramos;
    Sprite kd;

    public Scene2Outro() {
        super("scene2");
    }

    @Override
    public void create() {
        super.create();
        batch2 = new SpriteBatch();
        font3 = createFont(15);
        var lb = C64Colors.LIGHT_BLUE;
        font3.setColor(lb.getR(), lb.getG(), lb.getB(), 1f);

        ramos = new Sprite(new Texture("winter/ramos.jpg"));
        ramos.setScale(0.7f);
        ramos.setPosition(400, 100);
        kd = new Sprite(new Texture("winter/NRG25.jpg"));
        kd.setScale(0.5f);
        kd.setPosition(300, 100);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Globals.CURSOR_COLOR = C64Colors.LIGHT_BLUE;
        Globals.BKG_COLOR = C64Colors.BLUE;
        Globals.cursorY = Globals.DEFAULT_CURSOR_Y;

        long frame = Gdx.graphics.getFrameId();
        if (frame == 9301) {
            System.out.println(C64Helper.countElapsedTime());
        }
    }

    @Override
    public void render() {
        super.render();
        long frame = Gdx.graphics.getFrameId();

        batch2.begin();
        if (frame > 9300) {
            font3.draw(batch2, "PRINT (TIME$)", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y);
            font3.draw(batch2, C64Helper.countElapsedTime(), LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 15 - 1);
            font3.draw(batch2, "READY.", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 30 - 1);

            Globals.cursorY = Globals.DEFAULT_CURSOR_Y - 45 - 1;
        }

        if (frame > 9450) {
            font3.draw(batch2, "CODE & GFX:     KD", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 80);
        }

        if (frame > 9450 && frame < 9651) {
            kd.draw(batch2);
        }

        if (frame > 9650) {
            font3.draw(batch2, "MSX:            RAMOS", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 120);
            font3.draw(batch2, "https://csdb.dk/sid/?id=23782", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 235);
            ramos.draw(batch2);
        }

        if (frame > 9775) {
            font3.draw(batch2, "Almost 3 min demo is enough. Bye!", LEFT_EDGE, Globals.DEFAULT_CURSOR_Y - 235 - 26);
        }

        if (frame > 9900) {
            Gdx.app.exit();
            System.exit(0);
        }

        batch2.end();
    }
}

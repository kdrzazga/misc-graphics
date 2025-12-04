package org.kd.winter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.Globals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Scene1c64 extends BasicC64Screen {
    SpriteBatch batch2;
    ShapeRenderer shapeRenderer;
    Texture backgroundTexture;
    private Music kolendaRamosa;
    private List<Sprite> backgroundSprites;
    private List<Sprite> backgroundSprites2;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.createMusic();
        this.batch2 = new SpriteBatch(2);
        backgroundSprites = new ArrayList<>(2);
        backgroundSprites2 = new ArrayList<>(2);
        AtomicReference<Float> shift = new AtomicReference<>((float) 0);

        Arrays.asList("lda1.png", "sta$d020.png").forEach(picPath -> {
            initSprite(picPath, shift, backgroundSprites);
        });
        Arrays.asList("poke646_1.png", "clrscr.png").forEach(picPath -> {
            initSprite(picPath, shift, backgroundSprites2);
        });
    }

    private void initSprite(String picPath, AtomicReference<Float> shift, List<Sprite> spriteList) {
        var texture = new Texture("winter/" + picPath);
        var sprite = new Sprite(texture);
        sprite.setX(2 * 100 + shift.get());
        sprite.setY(2 * 160 + shift.get());
        shift.set(shift.get() - 30);
        spriteList.add(sprite);
    }

    @Override
    public void update(float delta) {
        long frame = Gdx.graphics.getFrameId();

        if (frame > 333 && frame < 333 + 7 * 50) {
            kolendaRamosa.play();
        }

        if (frame == 400) {
            this.backgroundColor = C64Colors.WHITE;
        }
        else if (frame == 600){
            Globals.CURSOR_COLOR = C64Colors.WHITE;
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        if (frame > 300 && frame < 777) {
            var spriteGroup = backgroundSprites;

            if (frame > 550) spriteGroup = backgroundSprites2;
            flySprites(spriteGroup);
        }
        batch2.end();
    }

    private void flySprites(List<Sprite> spriteGroup) {
        for (int i = 0; i < spriteGroup.size(); i++) {
            Sprite s = spriteGroup.get(i);
            flyCaptionSprite(s, i);
        }
    }

    private void flyCaptionSprite(Sprite s, int i) {
        s.draw(batch2);
        s.setX(s.getX() + (i + 1));
        s.setY(s.getY() - (i - 1));
        s.setScale(s.getScaleX() + 0.01f, s.getScaleY() + 0.01f);
        s.setAlpha(1f);
    }


    private void createMusic() {
        kolendaRamosa = Gdx.audio.newMusic(Gdx.files.internal("winter/HejKoledaRamos.mp3"));
        kolendaRamosa.setLooping(false);
        kolendaRamosa.setVolume(1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        kolendaRamosa.dispose();
        batch2.dispose();
        for (Sprite sprite : backgroundSprites) {
            sprite.getTexture().dispose();
        }
    }
}

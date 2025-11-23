package org.kd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Animation extends ApplicationAdapter {
    SpriteBatch batch;
    Texture texture;
    List<Sprite> sprites;
    private BitmapFont font;
    private BitmapFont font2;
    private Music music;
    private Music anotherVisitor;
    private long counter;

    @Override
    public void create() {
        counter = System.currentTimeMillis();

        this.sprites = new ArrayList<>(3);
        batch = new SpriteBatch(); //SpriteBatch is a renderer, used to effectively draw sprites
        var positions = Arrays.asList(50, 50, 250, 50, 250, 250);
        AtomicInteger index = new AtomicInteger(0);
        Arrays.asList("skull.bmp", "monster.bmp").forEach(file -> {
            texture = new Texture(Gdx.files.internal(file));

            var sprite = new Sprite(texture);

            sprite.setPosition(positions.get(index.get()), positions.get(index.get() + 1));
            index.addAndGet(2);
            sprite.setScale(2.0f);
            sprites.add(sprite);

            this.createFont();
            this.createMusic();
        });

        var monster = sprites.get(1);
        //monster.rotate90(false);
        //monster.setFlip(false,true);
        //monster.setAlpha(0.5f); //whole sprite becomes partly transparent, not only backgound
        //monster.setScale(10,3);
    }

    private void createFont() {
        var generator = new FreeTypeFontGenerator(Gdx.files.internal("dulski.ttf"));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        var parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        parameter2.size = 15;
        parameter2.color = Color.ORANGE;
        font = generator.generateFont(parameter);
        font2 = generator.generateFont(parameter2);
        generator.dispose();
    }

    private void createMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("modem.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        anotherVisitor = Gdx.audio.newMusic(Gdx.files.internal("anothervisitor.mp3"));
        anotherVisitor.setLooping(false);
        anotherVisitor.setVolume(1f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprites.forEach(s -> s.draw(batch));

        var skull = sprites.get(0);
        skull.setPosition(skull.getX() + 1, skull.getY());
        writeCustomSCII();
        batch.end();

        float mainThemeVolume = 1f;
        long frame = Gdx.graphics.getFrameId();
        if (frame > 333 && frame < 333 + 7 * 50) {
            mainThemeVolume = 0.1f;
            anotherVisitor.play();
        }
        music.setVolume(mainThemeVolume);
    }

    private void writeCustomSCII() {

        font.draw(batch, "MSX: MODEM BY WODNIK", 10, Gdx.graphics.getHeight() - 10);
        font2.draw(batch, "HTTPS://DEEPSID.CHORDIAN.NET/?FILE=MUSICIANS/W/WODNIK/MODEM.SID", 10, Gdx.graphics.getHeight() - 2 * 20);
        font.draw(batch, "0 1 2 3 4 5 6 7 8 9 ", 10, Gdx.graphics.getHeight() - 5 * 20);
        font.draw(batch, "a b d e f g h i j t u v w x y z ! * ( )", 10, Gdx.graphics.getHeight() - 6 * 20);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        music.dispose();
    }
}

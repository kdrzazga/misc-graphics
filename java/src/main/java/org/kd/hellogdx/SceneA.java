package org.kd.hellogdx;

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

public class SceneA extends Scene {
    SpriteBatch batch;
    Texture texture;
    List<Sprite> sprites;
    private BitmapFont font;
    private BitmapFont font2;
    private Music music;

    @Override
    public void create() {

        createSprites();
        this.createFont();
        this.createMusic();
    }

    @Override
    public void update(float delta) {
        // update scene-specific logic
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

    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene A");
    }

    private void createSprites() {
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
        music = Gdx.audio.newMusic(Gdx.files.internal("agemixer-audioman.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
    }


    private void writeCustomSCII() {

        font.draw(batch, "MSX: AUDIOMAN BY AGEMIXER", 10, Gdx.graphics.getHeight() - 10);
        font2.draw(batch, "KUDOZ TO PAN ARECZEK OF KAPLUS", 10, Gdx.graphics.getHeight() - 2 * 20);
        font.draw(batch, "0 1 2 3 4 5 6 7 8 9 ", 10, Gdx.graphics.getHeight() - 5 * 20);
        font.draw(batch, "a b d e f g h i j t u v w x y z ! * ( )", 10, Gdx.graphics.getHeight() - 6 * 20);
    }

}

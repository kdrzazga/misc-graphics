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
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Scene1c64 extends BasicC64Screen {
    SpriteBatch batch2;
    ShapeRenderer shapeRenderer;
    //Texture backgroundTexture;
    private Music kolendaRamosa;
    private List<Sprite> backgroundSprites;
    private List<Sprite> backgroundSprites2;
    private List<Sprite> snowflakes;
    private List<Point> snowPatches;
    private List<Sprite> letters;
    private float snowPatchTreshold = 90f;
    private Sprite backgroundSprite3;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth() , Gdx.graphics.getWidth());

        this.createMusic();
        this.batch2 = new SpriteBatch(2);
        backgroundSprites = new ArrayList<>(2);
        backgroundSprites2 = new ArrayList<>(2);
        snowPatches = new ArrayList<>();
        snowflakes = new ArrayList<>();
        letters = new ArrayList<>(5);
        AtomicReference<Float> shift = new AtomicReference<>((float) 0);

        Arrays.asList("lda1.png", "sta$d020.png").forEach(picPath -> {
            initSprite(picPath, shift, backgroundSprites);
        });
        Arrays.asList("poke646_1.png", "clrscr.png").forEach(picPath -> {
            initSprite(picPath, shift, backgroundSprites2);
        });

        AtomicReference<Float> shift2 = new AtomicReference<>((float) 0);
        Arrays.asList("m.png", "e.png", "r.png", "r.png", "y.png").forEach(picPath ->{
            initSprite(picPath, new AtomicReference<>((float) 0), letters);
            var letter = letters.get(letters.size() - 1);
            letter.setX(100*(shift2.get() + 1));
            letter.setY(Globals.SCREEN_HEIGHT - 220);
            letter.setScale(0.2f, 0.2f);
            shift2.set(shift2.get() + 1);
        });

        backgroundSprite3 = new Sprite(new Texture("winter/lightblu_bkgnd.png"));
        backgroundSprite3.setX(0);
        backgroundSprite3.setY(250);

        var flakeTexture = new Texture("winter/asterisk.png");
        for(int i = 0; i < 77; i++){
            var sprite = new Sprite(flakeTexture);
            sprite.setScale(0.6f, 0.6f);
            sprite.setPosition(35 + 14*i, (float) (Globals.SCREEN_HEIGHT - 24*Math.sin(i/Math.PI)) + i%5);
            this.snowflakes.add(sprite);
        }
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
            this.borderColor = C64Colors.WHITE;
        }
        else if (frame == 600){
            Globals.CURSOR_COLOR = C64Colors.WHITE;
        }
        else if (frame == 699){
            this.backgroundScreenPng = "winter/white-ready.png";
            this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
            Globals.cursorY = Math.round(0.753* Globals.SCREEN_HEIGHT) - 2;
        }

        else if (frame == 1100){
            this.backgroundScreenPng = "winter/lblue-ready.png";
            this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
            Globals.BKG_COLOR = C64Colors.LIGHT_BLUE;
        }

        for (int startIndex = 0; startIndex <= 6; startIndex++) {
            if (frame > 1100 + startIndex * 100) {
                for (int i = startIndex; i < snowflakes.size(); i += 7) {
                    var flake =snowflakes.get(i);
                    flake.setY(flake.getY() - 1);

                    if (flake.getY() <= 0)
                        flake.setY(Globals.SCREEN_WIDTH -40 - i % 5);
                }
            }
        }
        System.out.print(frame + " ");
        if (frame > 1499 && frame % 3 == 0){
            if (this.snowPatchTreshold <138f) this.snowPatchTreshold += 0.15f;
            int min = 50;
            int max = 800-50;
            int x = (int) (new Random().nextDouble() * (max - min) + min);
            int y = Math.round(this.snowPatchTreshold);
            var p = new Point(x, y);
            this.snowPatches.add(p);
            //System.out.println(p.getX() + " " + p.getY());
            if (x < min + max /2){
                this.snowPatches.add(new Point(x + 2, y +1));
            }
        }

        if (frame > 2730 && frame%2 == 0){
            Long index = frame % 5;
            var letter = letters.get(index.intValue());
            float scale = letter.getScaleX();
            if (scale < 1.1f){
                scale += 0.05f;
            }
            letter.setScale(scale, scale);
        }
        if(frame > 3255 && frame%2 == 0){
            if (letters.get(0).getY() > 360) {
                letters.get(0).setY(letters.get(0).getY() - 1);
                letters.get(4).setY(letters.get(4).getY() - 1);
            }
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        if (frame > 300 && frame < 700) {
            var spriteGroup = backgroundSprites;

            if (frame > 550) spriteGroup = backgroundSprites2;

            flySprites(spriteGroup);
        }
        if (frame > 900) {
            float x = backgroundSprite3.getX();
            backgroundSprite3.setX(x + 1.5f);
            double y= backgroundSprite3.getY() + 3.5*Math.sin(x / (4*Math.PI));
            backgroundSprite3.setY((float) y);
            backgroundSprite3.draw(batch2);
        }

        for (int startIndex = 0; startIndex <= 6; startIndex++) {
            if (frame > 1100 + startIndex * 100) {
                for (int i = startIndex; i < snowflakes.size(); i += 7) {
                    snowflakes.get(i).draw(batch2);
                }
            }
        }

        if (frame > 1234){
            this.snowPatches.forEach(point -> {
                font.draw(batch2, "X", point.getX(), point.getY());
            });
        }


        if (frame > 2730){
            letters.forEach(letter -> letter.draw(batch2));
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

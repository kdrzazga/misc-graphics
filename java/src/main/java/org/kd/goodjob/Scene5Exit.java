package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSprite;
import org.kd.common.C64Helper;
import org.kd.common.ConsoleLogger;
import org.kd.common.Scene;
import org.kd.common.tricks.Effects;
import org.kd.goodjob.appendix.BannerApple;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Scene5Exit extends Scene {

    public static final long START_FRAME = Scene4JobsReturn.START_FRAME + 4900;
    static final long DEMO_END_FRAME = START_FRAME + 1629;
    private SpriteBatch batch5;
    private Texture cake;
    private BitmapFont font, fontSmall;
    private final List<Message> messages;
    private Music endSpeech;
    private final List<AnimatedSprite> flameSprites;

    public Scene5Exit() {
        super("exit");
        var coords = List.of(new Point(36, 550), new Point(182, 528), new Point(370, 560)
                , new Point(648, 523), new Point(939, 500));
        var spriteCount = coords.size();
        this.flameSprites = new ArrayList<>(spriteCount);

        IntStream.range(0, spriteCount).forEach(i -> {
            var x = coords.get(i).getX();
            var y = coords.get(i).getY();
            var flame = new AnimatedSprite("good-job/flames/flame.png", 4, 0.05f + 0.008f * i, x, y);
            flame.scale(1f - 0.05f * i);
            if (List.of(0,1, 2).contains(i)) flame.scale(0.54f);
            this.flameSprites.add(flame);
        });

        messages = new ArrayList<>();
        messages.add(new Message("This demo is not intended as an advertisement of Apple company", 121));
        messages.add(new Message("But as a tribute to a great man, who created a great firm.", 391));
        messages.add(new Message("IT is a dynamic environment, and hardly any company is able ", 612));
        messages.add(new Message("to stay 50 years on a market...", 870));
        messages.add(new Message("and thrive.", 991));
    }

    @Override
    public void create() {
        batch5 = new SpriteBatch();
        this.cake = new Texture("good-job/cake.jpg");
        this.font = C64Helper.createFont(44, "DRENA.ttf");
        this.fontSmall = C64Helper.createFont(22, "Helvetica Regular.otf");
        this.endSpeech = Gdx.audio.newMusic(Gdx.files.internal("good-job/end-speech.mp3"));
    }

    @Override
    public void update(float delta) {
        ConsoleLogger.logBannerWithElapsedTime(BannerApple.lines);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch5.begin();
        batch5.draw(cake, 0, 0);
        this.flameSprites.forEach(flame -> flame.draw(batch5));
        if (getRelativeFrame() == 122) endSpeech.play();

        if (getRelativeFrame() > 122) {
            int baseY = Gdx.graphics.getHeight() - 30;
            int lineSpacing = 57;

            for (int i = 0; i < messages.size(); i++) {
                Message msg = messages.get(i);
                if (getRelativeFrame() > msg.frameThreshold) {
                    int yPosition = baseY - (i * lineSpacing);
                    font.draw(batch5, msg.text, 30, yPosition);
                }
            }

            if (Gdx.graphics.getFrameId() > DEMO_END_FRAME - 250) {
                //System.err.println("KD and RamOS");
                fontSmall.draw(batch5, "CODE & GFX: KD ", 0.85f * Gdx.graphics.getWidth(), cake.getHeight() - 60);
                fontSmall.draw(batch5, "MSX: RAMOS ", 0.85f * Gdx.graphics.getWidth(), cake.getHeight() - 80);
            }

            Effects.typewriter(batch5, font, 30, Gdx.graphics.getHeight() - 57 * messages.size() - 28, DEMO_END_FRAME - 300
                    , 60, "GOOD JOB MR. JOBS !!! !!! !!! !!! !!! !!! !!! !!! !!!", 1);
        }

        batch5.end();
    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - START_FRAME;
    }

    @Override
    public void dispose() {

    }

    static class Message {
        String text;
        int frameThreshold;

        Message(String text, int frameThreshold) {
            this.text = text;
            this.frameThreshold = frameThreshold;
        }
    }
}

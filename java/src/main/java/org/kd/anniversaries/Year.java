package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;
import org.kd.common.Helper;
import org.kd.common.Scene;

import java.util.ArrayList;
import java.util.List;

public abstract class Year {

    public static final long DEFAULT_DURATION = 600;

    protected final List<String> messages;
    protected final BitmapFont font1;
    protected final BitmapFont font2;
    protected final String statementPath;
    protected final long startingFrame;
    protected long endFrame;
    private boolean alreadySaid = false;
    private final Music statement;


    public Year(String statementPath, long startingFrame) {
        this.startingFrame = startingFrame;
        this.endFrame = this.startingFrame + Year.DEFAULT_DURATION; //can be overriden
        this.statementPath = statementPath;
        this.statement = Gdx.audio.newMusic(Gdx.files.internal(statementPath));
        font1 = C64Helper.createFont(32, "zx-spectrum.ttf");
        font2 = C64Helper.createFont(15, "C64_Pro_Mono-STYLE.ttf");
        this.messages = new ArrayList<>(1);
    }

    public void sayItOnce() {
        if (!alreadySaid) {
            var fr = Gdx.graphics.getFrameId();
            System.out.println(Helper.countElapsedTime() + " [frame " + fr + "] Statement for year " + this.getClass().getSimpleName());
            this.statement.play();
            this.alreadySaid = true;
        }
    }

    public void writeMessage(SpriteBatch batch, String message, float x, float y) {
        font2.draw(batch, message, x, y);
    }

    public abstract void draw(long frame, Scene scene);

    public long getStartingFrame() {
        return startingFrame;
    }

    public long getEndFrame() {
        return endFrame;
    }
}

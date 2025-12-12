package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public abstract class Year {

    public static final long DEFAULT_DURATION = 600;

    protected final BitmapFont zxSpectrumFont;
    protected final String statementPath;
    protected long startingFrame;
    protected long endFrame;
    private boolean alreadySaid = false;
    private final Music statement;


    protected Year(String statementPath, long startingFrame) {
        this.startingFrame = startingFrame;
        this.endFrame = this.startingFrame + Year.DEFAULT_DURATION; //can be overriden
        this.statementPath = statementPath;
        this.statement = Gdx.audio.newMusic(Gdx.files.internal(statementPath));
        zxSpectrumFont = C64Helper.createFont(32, "zx-spectrum.ttf");
    }

    public void sayItOnce() {
        if (!alreadySaid) {
            this.statement.play();
            this.alreadySaid = true;
        }
    }

    abstract void draw(long frame, Scene1c64 screen);

    public long getStartingFrame() {
        return startingFrame;
    }

    public long getEndFrame() {
        return endFrame;
    }
}

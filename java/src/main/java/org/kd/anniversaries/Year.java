package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public abstract class Year {

    protected final BitmapFont zxSpectrumFont;
    protected final String statementPath;
    private boolean alreadySaid = false;
    private final Music statement;

    public abstract void draw(long frame, Scene1c64 screen);

    protected Year(String statementPath) {
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
}

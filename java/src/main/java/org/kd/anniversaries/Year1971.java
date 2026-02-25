package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import org.kd.common.Scene;

public class Year1971 extends Year {

    private final Texture xeroxLaserPrinter;

    Year1971(long startingFrame) {
        super("anniversaries/ya/55 years ago.mp3", startingFrame);
        this.endFrame -= 2 * Year.DEFAULT_DURATION / 3;
        this.xeroxLaserPrinter = new Texture("anniversaries/HW/xerox-parc-printer.png");
    }

    @Override
    public void draw(long frame, Scene scene) {
        var screen = (Scene1c64) scene;
        screen.backgroundTexture = this.xeroxLaserPrinter;
        font1.draw(screen.batch2, "1971", 300, 570);
        this.writeMessage(screen.batch2, "Xerox PARC was the prototype for laser printers", 20, 530);

    }
}

package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import org.kd.common.Scene;

public class Year1981 extends Year {

    private final Texture gates;
    private final Texture vic20;

    Year1981(long startingFrame) {
        super("anniversaries/ya/45 years ago.mp3", startingFrame);
        this.gates = new Texture("anniversaries/os/gates.png");
        this.vic20 = new Texture("anniversaries/HW/vic20.png");
    }

    public void draw(long frame, Scene scene) {
        var screen = (Scene1c64) scene;
        font1.draw(screen.batch2, "1981", 300, 570);
        if (frame < this.startingFrame + 0.35 * Year.DEFAULT_DURATION) {
            font2.draw(screen.batch2, "  Commodore company released VIC 20", 123, 535);
            screen.backgroundTexture = this.vic20;
        } else if (frame < this.endFrame) {
            font2.draw(screen.batch2, "MICROSOFT", 236, 500);
            font2.draw(screen.batch2, "wrote PC-DOS", 220, 480);
            font2.draw(screen.batch2, "for IBM.", 205, 460);
            font2.draw(screen.batch2, "MS-DOS is", 205, 440);
            font2.draw(screen.batch2, "related, but", 202, 420);
            font2.draw(screen.batch2, "much younger", 198, 400);
            font2.draw(screen.batch2, "product.", 202, 380);
            screen.backgroundTexture = this.gates;
        }
    }
}

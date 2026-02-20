package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1976 extends Year{

    private final Texture jobsWozniak;

    Year1976(long startingFrame) {
        super("anniversaries/ya/50 years ago.mp3", startingFrame);
        this.jobsWozniak = new Texture("anniversaries/os/JobsWayneWozniak.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        font1.draw(screen.batch2, "1976", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.jobsWozniak;
        }

        this.writeMessage(screen.batch2, "Apple was founded on April 1, 1976", 100, 530);

        if (frame > this.startingFrame + Year.DEFAULT_DURATION / 4){
            this.writeMessage(screen.batch2, "Steve Jobs", 100, 97);
        }
        if (frame > this.startingFrame + Year.DEFAULT_DURATION / 2){
            this.writeMessage(screen.batch2, "Steve Wozniak", 500, 97);
        }
        if (frame > this.startingFrame + 3* Year.DEFAULT_DURATION / 4){
            this.writeMessage(screen.batch2, "Ronald Wayne", 290, 97);
        }
    }
}

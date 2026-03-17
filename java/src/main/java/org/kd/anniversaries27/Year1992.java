package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1992 extends Year {

    private final Sprite suse, dec;

    Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = 22300;
        var suseTexture = new Texture("anniversaries27/pics/1992/suse.png");
        suse = new Sprite(suseTexture);
        suse.setPosition(204, 201);
        suse.setScale(1.3f, 1.4f);

        var decTexture = new Texture("anniversaries27/pics/1992/DEC.jpg");
        dec = new Sprite(decTexture);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        long relFrame = frame - this.startingFrame;
        sayItOnce();

        batch.begin();

        if (2060 < relFrame && relFrame < 2400) {
            suse.draw(batch);
        } else if (1310 < relFrame && relFrame < 2100) {
            dec.draw(batch);
        }

        batch.end();

        log(relFrame);

        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {
            System.out.print("Grace Hopper ");
        } else if (relFrame == 360) {
            System.out.print("PC486DX2 ");
        } else if (relFrame == 710) {
            System.out.print("AVI ");
        } else if (relFrame == 1310) {
            System.out.print("DEC ");
        } else if (relFrame == 2100) {
            System.out.print("SuSe ");
        } else if (relFrame == 2500) {
            System.out.print("OpenGL ");
        } else if (relFrame == 3700) {
            System.out.print("Amigas ");
        } else if (relFrame == 4400) {
            System.out.println("MortalKombat");

        }
    }

}

package org.kd.kickass;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Globals;
import org.kd.common.Helper;

public class KickassDemo extends Game {

    protected Music tune;
    private Screen currentScreen, screen1, screen2, screen3, screen4;

    @Override
    public void create() {
        System.out.println(Globals.startTime);

        tune = Gdx.audio.newMusic(Gdx.files.internal("kickass/audio/PieceSzit2.mp3"));
        tune.setLooping(true);
        tune.setVolume(1f);

        screen1 = new Scene1KickScroll();
        screen2 = new Scene2Karateka();
        screen3 = new Scene3Counting();
        screen4 = new Scene4ComicStrips();
        currentScreen = screen1;
        setScreen(currentScreen);
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();
        currentScreen.render(frame);

        if (frame > 250) {
            tune.play();
        }

        if (frame == Scene2Karateka.START_FRAME) {
            System.out.println(Scene2Karateka.class.getSimpleName() + " started at " + Helper.countElapsedTime());
            currentScreen = screen2;
            setScreen(currentScreen);
        } else if (frame == Scene3Counting.START_FRAME) {
            System.out.println(Scene3Counting.class.getSimpleName() + " started at " + Helper.countElapsedTime());
            currentScreen = screen3;
            setScreen(currentScreen);
        } else if (frame == Scene4ComicStrips.START_FRAME) {
            System.out.println(Scene4ComicStrips.class.getSimpleName() + " started at " + Helper.countElapsedTime());
            currentScreen = screen4;
            setScreen(currentScreen);
        }
    }

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("KickAss Demo");
        config.width = 1700;
        config.height = 990;
        //config.fullscreen = false;
        new LwjglApplication(new KickassDemo(), config);
    }
}

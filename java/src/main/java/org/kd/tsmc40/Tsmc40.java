package org.kd.tsmc40;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Globals;
import org.kd.common.Helper;

public class Tsmc40 extends Game {

    protected Music tune;
    private Screen currentScreen, screen1, screen2, screen3, screen4;

    @Override
    public void create() {
        System.out.println(Globals.startTime);

        tune = Gdx.audio.newMusic(Gdx.files.internal("kickass/audio/PieceSzit2.mp3"));
        tune.setLooping(true);
        tune.setVolume(1f);

        screen1 = new Scene1Intro();
        screen2 = new Scene2City();
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

        if (frame == Scene2City.START_FRAME) {
            System.out.println(Scene2City.class.getSimpleName() + " started at " + Helper.countElapsedTime());
            currentScreen = screen2;
            setScreen(currentScreen);
        }

        System.out.print(frame + " ");
    }

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("TSMC 40 Demo");
        config.width = 1700;
        config.height = 990;
        //config.fullscreen = false;
        new LwjglApplication(new Tsmc40(), config);
    }
}

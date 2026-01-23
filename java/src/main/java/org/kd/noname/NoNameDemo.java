package org.kd.noname;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class NoNameDemo extends Game {

    protected Music tune;
    private Screen scene;

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("NoName Demo");
        config.width = 1700;
        config.height = 990;
        //config.fullscreen = false;
        new LwjglApplication(new NoNameDemo(), config);
    }

    @Override
    public void create() {

        tune = Gdx.audio.newMusic(Gdx.files.internal("noname/audio/PieceSzit2.mp3"));
        tune.setLooping(true);
        tune.setVolume(1f);

        scene = new Scene1KickScroll(this);
        setScreen(scene);
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();
        scene.render(frame);

        if (frame > 250){
            tune.play();
        }
    }
}
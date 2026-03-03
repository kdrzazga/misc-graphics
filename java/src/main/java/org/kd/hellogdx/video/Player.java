package org.kd.hellogdx.video;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;

import java.io.FileNotFoundException;

public class Player extends ApplicationAdapter {
    private VideoPlayer video;
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        video = VideoPlayerCreator.createVideoPlayer();

        video.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle fileHandle) {
                System.out.println("playback ends");
            }
        });

        try {
            video.play(Gdx.files.local("tsmc40/citytrip.mp4"));
        } catch (FileNotFoundException e) {
            Gdx.app.error("gdx-video", "No file");
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        video.update();

        batch.begin();
        var frameTexture = video.getTexture();
        if (frameTexture != null) {
            batch.draw(frameTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (video != null) {
            video.dispose();
        }
    }
    }
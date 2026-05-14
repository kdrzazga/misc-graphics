package org.kd.talkingheads;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.BasicC64Screen;

import java.io.FileNotFoundException;

public class SceneTramielLaugh extends BasicC64Screen {
    SpriteBatch batch;
    VideoPlayer videoPlayer;
    Texture texture;
    private SpriteBatch batch2;

    public SceneTramielLaugh() {
        super("Tramiel");
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
        batch = new SpriteBatch();

        // Initialize the VideoPlayer
        videoPlayer = VideoPlayerCreator.createVideoPlayer();

        videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle fileHandle) {
                System.out.println("Video ended.");
            }
        });

        try {
            videoPlayer.play(Gdx.files.local("talkingheads/TramielHaHa.mp4"));
        } catch (FileNotFoundException e) {
            Gdx.app.error("gdx-video","No Tramiel video");
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }
}

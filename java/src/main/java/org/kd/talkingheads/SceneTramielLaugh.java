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

        videoPlayer.setOnCompletionListener(_ -> System.out.println("Video ended."));

        try {
            String path = System.getProperty("user.dir") + "\\java\\src\\main\\resources\\talkingheads\\";
            System.out.println(path);
            var fh = new FileHandle(path +"TramielHaHa.mp4");
            //System.out.println(fh.exists());
            videoPlayer.play(fh);
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

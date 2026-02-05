package pl.kaplus.scissors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kd.common.tricks.TravellingLogo;

public class Scissors extends ApplicationAdapter {
    static final int SCREEN_WIDTH = 800;
    SpriteBatch batch;
    Texture backgroundTexture;
    Texture choinkaTxtr;
    TravellingLogo logo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("c64.png");
        choinkaTxtr = new Texture("winter/choinka.png");
        var logoTxtr = new Texture(Gdx.files.internal("anniversaries/logo.png"));
        logo = new TravellingLogo(logoTxtr, SCREEN_WIDTH, 200, 1000, 50);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        logo.move(deltaTime, SCREEN_WIDTH);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, SCREEN_WIDTH, 600);
        batch.draw(choinkaTxtr, 250, 50, choinkaTxtr.getWidth(), choinkaTxtr.getHeight());
        logo.draw(batch, SCREEN_WIDTH, 600);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
    }
}

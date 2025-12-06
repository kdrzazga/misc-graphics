package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BasicC64Screen extends Scene {
    protected static final int LEFT_EDGE = 82;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    protected Texture backgroundTexture;
    protected C64Colors borderColor;
    protected String backgroundScreenPng;
    protected BitmapFont font;

    public BasicC64Screen(String id) {
        super(id);
        this.backgroundScreenPng = "c64.png";
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
        this.borderColor = C64Colors.LIGHT_BLUE;
        font = createFont(26);
    }

    @Override
    public void update(float delta) {
        // update scene-specific logic
    }

    @Override
    public void render() {
        var lb = this.borderColor;
        Gdx.gl.glClearColor(lb.getR(), lb.getG(), lb.getB(), 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        long frame = Gdx.graphics.getFrameId();
        drawC64(frame);
    }

    private void drawC64(long frame) {
        float scale = 2f; // be careful with  scaling, results can be pathetic

        float scaledWidth = backgroundTexture.getWidth() * scale;
        float scaledHeight = backgroundTexture.getHeight() * scale;
        float x = (Gdx.graphics.getWidth() - scaledWidth) / 2;
        float y = (Gdx.graphics.getHeight() - scaledHeight) / 2;

        batch.begin();
        batch.draw(backgroundTexture, x, y, scaledWidth, scaledHeight);
        batch.end();
        C64Helper.blinkCursor(frame, shapeRenderer);
    }

    protected BitmapFont createFont(int size){
        var generator = new FreeTypeFontGenerator(Gdx.files.internal("C64_Pro_Mono-STYLE.ttf"));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size; // font size
        BitmapFont font = generator.generateFont(parameter);
        font.setColor(C64Colors.WHITE.getR(), C64Colors.WHITE.getG(), C64Colors.WHITE.getB(),0.75f);
        generator.dispose();
        return font;
    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene1Tiles");
        batch.dispose();
        backgroundTexture.dispose();
    }
}

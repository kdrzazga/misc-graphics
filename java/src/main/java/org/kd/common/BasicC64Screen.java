package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BasicC64Screen extends Scene {
    protected static final int LEFT_EDGE = 82;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    public Texture backgroundTexture;
    protected C64Colors borderColor;
    protected boolean blinkingCursor = true;
    protected String backgroundScreenPng;
    protected BitmapFont font;
    protected float x;
    protected float y;
    protected float scale = 2f; // be careful with  scaling, results can be pathetic

    public BasicC64Screen(String id) {
        super(id);
        this.backgroundScreenPng = "c64.png";
    }

    @Override
    public void create() {
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
        this.borderColor = C64Colors.LIGHT_BLUE;
        font = C64Helper.createFont(26,"C64_Pro_Mono-STYLE.ttf");
        float scaledWidth = backgroundTexture.getWidth() * this.scale;
        float scaledHeight = backgroundTexture.getHeight() * this.scale;
        this.x = (Gdx.graphics.getWidth() - scaledWidth) / 2;
        this.y = (Gdx.graphics.getHeight() - scaledHeight) / 2;
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
        float scaledWidth = backgroundTexture.getWidth() * this.scale;
        float scaledHeight = backgroundTexture.getHeight() * this.scale;

        batch.begin();
        batch.draw(backgroundTexture, this.x, this.y, scaledWidth, scaledHeight);
        batch.end();
        if (this.blinkingCursor) C64Helper.blinkCursor(frame, shapeRenderer);
    }

    @Override
    public void dispose() {
        System.out.println("Disposing Scene1Tiles");
        batch.dispose();
        backgroundTexture.dispose();
    }
}

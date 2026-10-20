package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.ByteBufferSeekableByteChannel;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.ColorUtil;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;

/**
 * Plays the video track of an MP4 (H.264, best encoded as baseline profile) with JCodec.
 * Audio is ignored. The file is read with Gdx.files.internal, so it also works inside the JAR.
 * Video frames are advanced by render frames, assuming the demo runs at 60 fps.
 */
public class VideoClip {

    private final int renderFps = 60;

    private final byte[] videoBytes;
    private final int videoFps;
    private final int x, y, drawWidth, drawHeight;
    private final boolean looping;

    private FrameGrab frameGrab;
    private Pixmap pixmap;
    private Texture texture;
    private byte[] rgbBytes;
    private long renderedFrames = 0;
    private long decodedFrames = 0;
    private boolean finished = false;

    public VideoClip(String internalPath, int videoFps, int x, int y, int drawWidth, int drawHeight, boolean looping) {
        this.videoBytes = Gdx.files.internal(internalPath).readBytes();
        this.videoFps = videoFps;
        this.x = x;
        this.y = y;
        this.drawWidth = drawWidth;
        this.drawHeight = drawHeight;
        this.looping = looping;
        this.frameGrab = openFrameGrab();
    }

    public void draw(SpriteBatch batch) {
        long targetVideoFrame = renderedFrames * videoFps / renderFps;
        while (!finished && decodedFrames <= targetVideoFrame)
            decodeNextFrame();
        renderedFrames++;

        if (texture != null)
            batch.draw(texture, x, y, drawWidth, drawHeight);
    }

    /** Shows the first frame as a still, without starting playback; draw() then plays from that frame. */
    public void drawFirstFrame(SpriteBatch batch) {
        if (texture == null && !finished)
            decodeNextFrame();
        if (texture != null)
            batch.draw(texture, x, y, drawWidth, drawHeight);
    }

    public boolean isFinished() {
        return finished;
    }

    public void dispose() {
        if (texture != null)
            texture.dispose();
        if (pixmap != null)
            pixmap.dispose();
    }

    private FrameGrab openFrameGrab() {
        try {
            var channel = ByteBufferSeekableByteChannel.readFromByteBuffer(ByteBuffer.wrap(videoBytes));
            return FrameGrab.createFrameGrab(channel);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (JCodecException e) {
            throw new IllegalStateException("Cannot decode video", e);
        }
    }

    private void decodeNextFrame() {
        Picture yuvPicture = readNativeFrame();
        if (yuvPicture == null && looping) {
            frameGrab = openFrameGrab();
            yuvPicture = readNativeFrame();
        }
        if (yuvPicture == null) {
            finished = true;
            return;
        }
        decodedFrames++;
        uploadToTexture(yuvPicture);
    }

    private Picture readNativeFrame() {
        try {
            return frameGrab.getNativeFrame();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void uploadToTexture(Picture yuvPicture) {
        int width = yuvPicture.getCroppedWidth();
        int height = yuvPicture.getCroppedHeight();
        if (pixmap == null) {
            pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
            texture = new Texture(pixmap);
            rgbBytes = new byte[width * height * 3];
        }

        var rgbPicture = Picture.create(yuvPicture.getWidth(), yuvPicture.getHeight(), ColorSpace.RGB);
        ColorUtil.getTransform(yuvPicture.getColor(), ColorSpace.RGB).transform(yuvPicture, rgbPicture);

        // JCodec stores samples as (value - 128); the decoded picture may be padded beyond the cropped size
        byte[] signedRgb = rgbPicture.getPlaneData(0);
        int paddedRowBytes = rgbPicture.getWidth() * 3;
        int rowBytes = width * 3;
        for (int row = 0; row < height; row++) {
            int source = row * paddedRowBytes;
            int target = row * rowBytes;
            for (int i = 0; i < rowBytes; i++)
                rgbBytes[target + i] = (byte) (signedRgb[source + i] + 128);
        }

        var pixels = pixmap.getPixels();
        pixels.clear();
        pixels.put(rgbBytes);
        pixels.flip();
        texture.draw(pixmap, 0, 0);
    }
}

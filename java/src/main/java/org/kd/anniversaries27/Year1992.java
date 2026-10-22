package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import org.kd.common.C64Helper;
import org.kd.common.FittedPicture;
import org.kd.common.TypedCode;
import org.kd.common.VideoClip;

import java.nio.IntBuffer;
import java.util.List;

public final class Year1992 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle amiga600Area = new Rectangle(114, 393, 378, 252);
    private final Rectangle amiga1200Area = new Rectangle(114, 122, 378, 252);
    private final Rectangle amiga4000Area = new Rectangle(502, 122, 389, 523);
    private final String picturesPath = "anniversaries27/pics/1992/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 1992.mp3.
    private final long pc486Frame = seconds(5);
    private final long aviFrame = seconds(11);
    private final long decFrame = seconds(21);
    private final long suseFrame = seconds(35);
    private final long suseLogoFrame = seconds(38.5);
    private final long openGlFrame = seconds(42);
    private final long amiga600Frame = seconds(60);
    private final long amiga1200Frame = seconds(64);
    private final long amiga4000Frame = seconds(68);
    private final long mortalKombatFrame = seconds(72);

    private final long fadeInFrames = 15;
    private final long amigaFadeInFrames = 30;

    // What S.u.S.E. stood for, typed over the darkened headquarters in the last 2 seconds before the logo
    private final long suseNameFrame = suseLogoFrame - seconds(2);
    private final long suseNameTypingFrames = seconds(1);
    private final int suseNameFontSize = 96;
    private final float suseNameLineSpacing = 118;
    private final float suseNameMargin = 40;
    private final float suseNameDimming = 0.6f;

    private final VideoClip graceHopper, mortalKombat;
    private final FittedPicture pc486, suseLogo, amiga600, amiga1200, amiga4000;
    private final AcronymAnimation avi;
    private final OpenGlTunnel openGlTunnel;
    private final TypedCode suseName;
    private final Sprite suse, dec;

    Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = startingFrame + 4888; // 1992.mp3 lasts 81.456 s at 60 fps

        // 360x434 video fitted to the canvas height: 450x543, centred horizontally
        graceHopper = new VideoClip("anniversaries27/video/grace.mp4", 30, 277, 112, 450, 543, false);
        // 320x240 video fitted to the canvas height: 724x543, centred horizontally
        mortalKombat = new VideoClip("anniversaries27/video/mk.mp4", 30, 140, 112, 724, 543, false);
        pc486 = new FittedPicture(picturesPath + "486.jpg", canvas, Color.BLACK);
        avi = new AcronymAnimation(List.of("Audio", "Video", "Interleave"), canvas);

        var decTexture = new Texture(picturesPath + "DEC.jpg");
        dec = new Sprite(decTexture);
        dec.setPosition(204, 201);

        var suseTexture = new Texture(picturesPath + "suse.png");
        suse = new Sprite(suseTexture);
        suse.setPosition(204, 201);
        suse.setScale(1.3f, 1.3f);
        suseLogo = new FittedPicture(picturesPath + "suse-logo.png", canvas, Color.BLACK, true);
        suseName = new TypedCode(List.of("Software-", "und", "System-", "Entwicklung"),
                C64Helper.createFont(suseNameFontSize, "Helvetica Regular.otf"), suseNameLineSpacing, Color.WHITE,
                suseNameTypingFrames);

        openGlTunnel = new OpenGlTunnel(picturesPath + "opengl.png");
        amiga600 = new FittedPicture(picturesPath + "a600.jpg", amiga600Area, Color.BLACK);
        amiga1200 = new FittedPicture(picturesPath + "a1200.jpg", amiga1200Area, Color.BLACK);
        amiga4000 = new FittedPicture(picturesPath + "a4000.jpg", amiga4000Area, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        long relFrame = frame - this.startingFrame;
        sayItOnce();

        if (relFrame < pc486Frame)
            drawVideo(batch, graceHopper);
        else if (relFrame < aviFrame)
            drawWhole(batch, pc486, relFrame, pc486Frame);
        else if (relFrame < decFrame)
            avi.draw(batch, relFrame - aviFrame);
        else if (relFrame < suseFrame)
            drawSprite(batch, dec);
        else if (relFrame < suseLogoFrame) {
            drawSprite(batch, suse);
            if (relFrame >= suseNameFrame)
                drawSuseName(batch, relFrame);
        } else if (relFrame < openGlFrame)
            drawWhole(batch, suseLogo, relFrame, suseLogoFrame);
        else if (relFrame < amiga600Frame)
            openGlTunnel.draw(relFrame - openGlFrame);
        else if (relFrame < mortalKombatFrame)
            drawAmigas(batch, relFrame);
        else
            drawVideo(batch, mortalKombat);

        log(relFrame);

        writeYear(batch);
    }

    private void drawVideo(SpriteBatch batch, VideoClip video) {
        batch.begin();
        video.draw(batch);
        batch.end();
    }

    private void drawSprite(SpriteBatch batch, Sprite sprite) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /** Amiga 600 first, then the Amiga 1200 joins below it, finally the Amiga 4000 on the right. */
    private void drawAmigas(SpriteBatch batch, long relFrame) {
        batch.begin();
        amiga600.drawWhole(batch, fadeIn(relFrame, amiga600Frame, amigaFadeInFrames));
        if (relFrame >= amiga1200Frame)
            amiga1200.drawWhole(batch, fadeIn(relFrame, amiga1200Frame, amigaFadeInFrames));
        if (relFrame >= amiga4000Frame)
            amiga4000.drawWhole(batch, fadeIn(relFrame, amiga4000Frame, amigaFadeInFrames));
        batch.end();
    }

    /** Software- / und / System- / Entwicklung, left aligned and centred vertically on the dimmed headquarters. */
    private void drawSuseName(SpriteBatch batch, long relFrame) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, suseNameDimming * fadeIn(relFrame, suseNameFrame, fadeInFrames));
        shapeRenderer.rect(canvas.x, canvas.y, canvas.width, canvas.height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        float top = canvas.y + canvas.height / 2 + (3 * suseNameLineSpacing + suseNameFontSize) / 2;
        suseName.draw(batch, canvas.x + suseNameMargin, top, relFrame - suseNameFrame);
    }

    private void drawWhole(SpriteBatch batch, FittedPicture picture, long relFrame, long appearFrame) {
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, appearFrame, fadeInFrames));
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame, long durationFrames) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) durationFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private final class OpenGlTunnel {

        private final float halfWidth = 2f;
        private final float halfHeight = 1.5f;
        private final float length = 80f;
        private final float tileSize = 2f;
        private final float speed = 8f;
        private final float fogDistance = 36f;
        private final float fieldOfView = 60f;
        private final float logoHeight = 1.3f;
        private final int logoCardPadding = 12;
        private final long tunnelFadeInFrames = 30;

        private final String vertexShader = """
                attribute vec4 a_position;
                attribute vec2 a_texCoord0;
                uniform mat4 u_projTrans;
                uniform float u_offset;
                varying vec2 v_texCoord;
                varying float v_depth;
                void main() {
                    v_texCoord = a_texCoord0 + vec2(0.0, u_offset);
                    gl_Position = u_projTrans * a_position;
                    v_depth = gl_Position.w;
                }
                """;
        private final String fragmentShader = """
                #ifdef GL_ES
                precision mediump float;
                #endif
                uniform sampler2D u_texture;
                uniform float u_fogDistance;
                uniform float u_brightness;
                varying vec2 v_texCoord;
                varying float v_depth;
                void main() {
                    vec4 color = texture2D(u_texture, v_texCoord);
                    float light = 1.0 - clamp(v_depth / u_fogDistance, 0.0, 1.0);
                    gl_FragColor = vec4(color.rgb * light * u_brightness, color.a);
                }
                """;

        private final ShaderProgram shader;
        private final Texture wallTexture, floorTexture, logoTexture;
        private final Mesh walls, floor, logo;
        private final PerspectiveCamera camera;
        private final Matrix4 logoTransform = new Matrix4();
        private final Matrix4 logoProjection = new Matrix4();
        private final IntBuffer viewport = BufferUtils.newIntBuffer(16);

        OpenGlTunnel(String logoPath) {
            shader = new ShaderProgram(vertexShader, fragmentShader);
            if (!shader.isCompiled())
                throw new IllegalStateException(shader.getLog());

            wallTexture = gridTexture();
            floorTexture = checkerTexture();
            logoTexture = logoCard(logoPath);

            walls = mesh(
                    surface(-halfWidth, -halfHeight, -halfWidth, halfHeight),
                    surface(halfWidth, -halfHeight, halfWidth, halfHeight),
                    surface(-halfWidth, halfHeight, halfWidth, halfHeight));
            floor = mesh(surface(-halfWidth, -halfHeight, halfWidth, -halfHeight));
            float logoHalfWidth = logoHeight * logoTexture.getWidth() / logoTexture.getHeight() / 2;
            float logoHalfHeight = logoHeight / 2;
            logo = mesh(new float[]{
                    -logoHalfWidth, -logoHalfHeight, 0, 0, 1,
                    logoHalfWidth, -logoHalfHeight, 0, 1, 1,
                    logoHalfWidth, logoHalfHeight, 0, 1, 0,
                    -logoHalfWidth, logoHalfHeight, 0, 0, 0});

            camera = new PerspectiveCamera(fieldOfView, canvas.width, canvas.height);
            camera.near = 0.1f;
            camera.far = length;
        }

        void draw(long animationFrame) {
            float time = animationFrame / 60f;
            camera.position.set(0.35f * MathUtils.sin(time * 0.9f), 0.2f * MathUtils.sin(time * 1.3f), 0);
            camera.direction.set(0, 0, -1);
            camera.up.set(0.12f * MathUtils.sin(time * 0.7f), 1, 0).nor();
            camera.update();

            Gdx.gl.glGetIntegerv(GL20.GL_VIEWPORT, viewport);
            Gdx.gl.glViewport((int) canvas.x, (int) canvas.y, (int) canvas.width, (int) canvas.height);
            Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
            Gdx.gl.glDisable(GL20.GL_CULL_FACE);
            Gdx.gl.glDisable(GL20.GL_BLEND);

            shader.bind();
            shader.setUniformi("u_texture", 0);
            shader.setUniformf("u_fogDistance", fogDistance);
            shader.setUniformf("u_brightness", Math.min(1f, animationFrame / (float) tunnelFadeInFrames));

            shader.setUniformMatrix("u_projTrans", camera.combined);
            shader.setUniformf("u_offset", time * speed / tileSize);
            wallTexture.bind(0);
            walls.render(shader, GL20.GL_TRIANGLES);
            floorTexture.bind(0);
            floor.render(shader, GL20.GL_TRIANGLES);

            shader.setUniformMatrix("u_projTrans", logoProjection.set(camera.combined).mul(logoTransform(time)));
            shader.setUniformf("u_offset", 0);
            logoTexture.bind(0);
            logo.render(shader, GL20.GL_TRIANGLES);

            Gdx.gl.glViewport(viewport.get(0), viewport.get(1), viewport.get(2), viewport.get(3));
        }

        private Matrix4 logoTransform(float time) {
            float z = -40 + 34 * smooth(time, 0, 3) - 6 * bump(time, 3, 4) + 3.2f * bump(time, 11, 2.5f)
                    - 4 * bump(time, 13.5f, 1.5f);
            float orbitRadius = 0.8f * bump(time, 7, 6);
            float orbitAngle = MathUtils.PI2 * 2 * progress(time, 7, 6);
            float x = orbitRadius * MathUtils.cos(orbitAngle);
            float y = 0.7f * orbitRadius * MathUtils.sin(orbitAngle);

            float spin = 720 * smooth(time, 0, 3) + 360 * smooth(time, 11, 4);
            float roll = 360 * smooth(time, 3, 4);
            float tumble = 360 * smooth(time, 7, 4);
            float pulse = time < 15 ? 1 : 1 + 0.18f * MathUtils.sin(MathUtils.PI2 * 2 * (time - 15)) * (1 - progress(time, 15, 3));

            return logoTransform.idt().translate(x, y, z)
                    .rotate(Vector3.Y, spin).rotate(Vector3.X, tumble).rotate(Vector3.Z, roll)
                    .scale(pulse, pulse, 1);
        }

        private float progress(float time, float start, float duration) {
            return MathUtils.clamp((time - start) / duration, 0f, 1f);
        }

        private float smooth(float time, float start, float duration) {
            return Interpolation.smooth.apply(progress(time, start, duration));
        }

        private float bump(float time, float start, float duration) {
            return MathUtils.sin(MathUtils.PI * progress(time, start, duration));
        }

        private float[] surface(float x1, float y1, float x2, float y2) {
            float across = (float) Math.hypot(x2 - x1, y2 - y1) / tileSize;
            float along = length / tileSize;
            return new float[]{
                    x1, y1, 0, 0, 0,
                    x2, y2, 0, across, 0,
                    x2, y2, -length, across, along,
                    x1, y1, -length, 0, along};
        }

        private Mesh mesh(float[]... quads) {
            int floatsPerQuad = 20;
            var vertices = new float[quads.length * floatsPerQuad];
            var indices = new short[quads.length * 6];
            for (int i = 0; i < quads.length; i++) {
                System.arraycopy(quads[i], 0, vertices, i * floatsPerQuad, floatsPerQuad);
                short first = (short) (i * 4);
                System.arraycopy(new short[]{first, (short) (first + 1), (short) (first + 2),
                        (short) (first + 2), (short) (first + 3), first}, 0, indices, i * 6, 6);
            }
            var mesh = new Mesh(true, quads.length * 4, indices.length,
                    VertexAttribute.Position(), VertexAttribute.TexCoords(0));
            mesh.setVertices(vertices);
            mesh.setIndices(indices);
            return mesh;
        }

        private Texture gridTexture() {
            var pixmap = new Pixmap(128, 128, Pixmap.Format.RGBA8888);
            pixmap.setColor(0.03f, 0.06f, 0.16f, 1);
            pixmap.fill();
            pixmap.setColor(0.08f, 0.2f, 0.45f, 1);
            pixmap.fillRectangle(0, 62, 128, 4);
            pixmap.fillRectangle(62, 0, 4, 128);
            pixmap.setColor(0.15f, 0.45f, 0.95f, 1);
            pixmap.fillRectangle(0, 0, 128, 6);
            pixmap.fillRectangle(0, 0, 6, 128);
            return repeatingTexture(pixmap);
        }

        private Texture checkerTexture() {
            var pixmap = new Pixmap(128, 128, Pixmap.Format.RGBA8888);
            pixmap.setColor(0.12f, 0.12f, 0.16f, 1);
            pixmap.fill();
            pixmap.setColor(0.7f, 0.72f, 0.78f, 1);
            pixmap.fillRectangle(0, 0, 64, 64);
            pixmap.fillRectangle(64, 64, 64, 64);
            return repeatingTexture(pixmap);
        }

        private Texture repeatingTexture(Pixmap pixmap) {
            var texture = new Texture(pixmap, true);
            texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
            texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
            pixmap.dispose();
            return texture;
        }

        private Texture logoCard(String path) {
            var logoPixmap = new Pixmap(Gdx.files.internal(path));
            var card = new Pixmap(logoPixmap.getWidth() + 2 * logoCardPadding,
                    logoPixmap.getHeight() + 2 * logoCardPadding, Pixmap.Format.RGBA8888);
            card.setColor(Color.WHITE);
            card.fill();
            card.drawPixmap(logoPixmap, logoCardPadding, logoCardPadding);
            logoPixmap.dispose();
            var texture = new Texture(card, true);
            texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
            card.dispose();
            return texture;
        }
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("Grace Hopper ");
        } else if (relFrame == pc486Frame) {
            System.out.print("PC486DX2 ");
        } else if (relFrame == aviFrame) {
            System.out.print("AVI ");
        } else if (relFrame == decFrame) {
            System.out.print("DEC ");
        } else if (relFrame == suseFrame) {
            System.out.print("SuSe ");
        } else if (relFrame == openGlFrame) {
            System.out.print("OpenGL ");
        } else if (relFrame == amiga600Frame) {
            System.out.print("Amigas ");
        } else if (relFrame == mortalKombatFrame) {
            System.out.println("MortalKombat");
        }
    }
}

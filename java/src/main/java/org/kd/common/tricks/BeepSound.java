package org.kd.common.tricks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;

public class BeepSound {
    private static final int SAMPLE_RATE = 44100;
    private static final int DURATION_MS = 50;    // Duration in milliseconds

    private final AudioDevice audioDevice;

    public BeepSound() {
        audioDevice = Gdx.audio.newAudioDevice(SAMPLE_RATE, true);
    }

    public void playBeep(int frequency) {
        int numSamples = (SAMPLE_RATE * DURATION_MS / 1000);
        short[] samples = new short[numSamples];

        for (int i = 0; i < numSamples; i++) {
            double t = i / (double) SAMPLE_RATE;
            // Generate sine wave
            double sineValue = Math.sin(2 * Math.PI * frequency * t);
            // Convert to short (-32768 to 32767)
            samples[i] = (short) (sineValue * Short.MAX_VALUE);
        }

        audioDevice.writeSamples(samples, 0, numSamples);
    }

    public void dispose() {
        if (audioDevice != null) {
            audioDevice.dispose();
        }
    }
}

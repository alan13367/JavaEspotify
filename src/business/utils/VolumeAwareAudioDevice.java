package business.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.lang.reflect.Field;

/**
 * Extension of JavaSoundAudioDevice that supports volume control.
 * Uses reflection to access the underlying SourceDataLine.
 */
public class VolumeAwareAudioDevice extends JavaSoundAudioDevice {

    private float currentVolume = 1.0f;
    private SourceDataLine cachedSource = null;

    /**
     * Sets the volume for the audio device.
     * @param volume Volume level between 0.0 (silent) and 1.0 (maximum)
     */
    public void setVolume(float volume) {
        this.currentVolume = volume;
        applyVolume();
    }

    private void applyVolume() {
        try {
            SourceDataLine source = getSource();
            if (source != null && source.isOpen() && source.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                
                // Convert linear volume (0.0-1.0) to decibels
                float dB;
                if (currentVolume <= 0.0001f) {
                    dB = gainControl.getMinimum();
                } else {
                    dB = (float) (Math.log10(currentVolume) * 20.0);
                    // Clamp to valid range
                    dB = Math.max(dB, gainControl.getMinimum());
                    dB = Math.min(dB, gainControl.getMaximum());
                }
                
                gainControl.setValue(dB);
            }
        } catch (Exception e) {
            // Silently fail if we can't set volume (e.g. control not supported)
            // System.err.println("Failed to set volume: " + e.getMessage());
        }
    }

    private SourceDataLine getSource() {
        if (cachedSource != null) {
            return cachedSource;
        }
        
        try {
            Field sourceField = JavaSoundAudioDevice.class.getDeclaredField("source");
            sourceField.setAccessible(true);
            cachedSource = (SourceDataLine) sourceField.get(this);
            return cachedSource;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void write(short[] samples, int offs, int len) throws JavaLayerException {
        // Ensure we try to capture source and apply volume once it's available
        super.write(samples, offs, len);
        
        // If we haven't cached the source yet, try to get it and apply volume
        if (cachedSource == null) {
            getSource();
            if (cachedSource != null) {
                applyVolume();
            }
        }
    }
    
    // Also try to apply volume on flush, just in case
    @Override
    public void flush() {
        super.flush();
        applyVolume();
    }
}

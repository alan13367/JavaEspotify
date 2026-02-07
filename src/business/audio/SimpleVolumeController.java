package business.audio;

/**
 * Simple volume controller that maintains volume state.
 * Note: Actual volume control is handled at the player level since JLayer 
 * doesn't provide direct volume control APIs.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 06/02/2026
 */
public class SimpleVolumeController implements VolumeController {

    private static final float DEFAULT_VOLUME = 0.5f;

    private float currentVolume;
    private boolean isMuted;
    private final java.util.List<VolumeChangeListener> listeners;

    /**
     * Creates a new SimpleVolumeController with default volume (50%).
     */
    public SimpleVolumeController() {
        this.listeners = new java.util.ArrayList<>();
        this.currentVolume = DEFAULT_VOLUME;
        this.isMuted = false;
    }

    @Override
    public void setVolume(float volume) {
        if (volume < 0.0f) {
            volume = 0.0f;
        } else if (volume > 1.0f) {
            volume = 1.0f;
        }

        this.currentVolume = volume;

        if (isMuted && volume > 0.0f) {
            isMuted = false;
            notifyMuteStateChanged();
        }

        notifyVolumeChanged();
    }

    @Override
    public float getVolume() {
        return currentVolume;
    }

    @Override
    public void mute() {
        if (!isMuted) {
            isMuted = true;
            notifyMuteStateChanged();
        }
    }

    @Override
    public void unmute() {
        if (isMuted) {
            isMuted = false;
            notifyMuteStateChanged();
        }
    }

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Gets the effective volume (0 if muted, otherwise current volume).
     * @return Effective volume level
     */
    public float getEffectiveVolume() {
        return isMuted ? 0.0f : currentVolume;
    }

    @Override
    public void addVolumeChangeListener(VolumeChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeVolumeChangeListener(VolumeChangeListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all listeners that the volume has changed.
     */
    private void notifyVolumeChanged() {
        for (VolumeChangeListener listener : new java.util.ArrayList<>(listeners)) {
            try {
                listener.onVolumeChanged(currentVolume);
            } catch (Exception e) {
                System.err.println("Error notifying volume listener: " + e.getMessage());
            }
        }
    }

    /**
     * Notifies all listeners that the mute state has changed.
     */
    private void notifyMuteStateChanged() {
        for (VolumeChangeListener listener : new java.util.ArrayList<>(listeners)) {
            try {
                listener.onMuteStateChanged(isMuted);
            } catch (Exception e) {
                System.err.println("Error notifying mute listener: " + e.getMessage());
            }
        }
    }
}

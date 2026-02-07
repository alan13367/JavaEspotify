package business.audio;

/**
 * Listener interface for volume change events.
 * Implement this interface to receive notifications when volume or mute state changes.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 06/02/2026
 */
public interface VolumeChangeListener {

    /**
     * Called when the volume level changes.
     *
     * @param newVolume The new volume level (0.0 to 1.0)
     */
    void onVolumeChanged(float newVolume);

    /**
     * Called when the mute state changes.
     *
     * @param isMuted true if audio is now muted, false if unmuted
     */
    void onMuteStateChanged(boolean isMuted);
}

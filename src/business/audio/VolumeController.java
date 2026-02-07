package business.audio;

/**
 * Interface for volume control operations.
 * Provides methods to control audio volume and mute state.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 06/02/2026
 */
public interface VolumeController {

    /**
     * Sets the volume level.
     *
     * @param volume Volume level between 0.0 (silent) and 1.0 (maximum)
     */
    void setVolume(float volume);

    /**
     * Gets the current volume level.
     *
     * @return Current volume level between 0.0 and 1.0
     */
    float getVolume();

    /**
     * Mutes the audio output.
     */
    void mute();

    /**
     * Unmutes the audio output.
     */
    void unmute();

    /**
     * Checks if the audio is currently muted.
     *
     * @return true if muted, false otherwise
     */
    boolean isMuted();

    /**
     * Gets the effective volume (0 if muted, otherwise current volume).
     * @return Effective volume level
     */
    float getEffectiveVolume();

    /**
     * Adds a listener to be notified of volume changes.
     *
     * @param listener The listener to add
     */
    void addVolumeChangeListener(VolumeChangeListener listener);

    /**
     * Removes a volume change listener.
     *
     * @param listener The listener to remove
     */
    void removeVolumeChangeListener(VolumeChangeListener listener);
}

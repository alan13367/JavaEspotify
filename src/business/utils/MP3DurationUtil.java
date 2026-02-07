package business.utils;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class for detecting real MP3 file duration using JLayer.
 * Reads MP3 frames to calculate actual playback duration.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 06/02/2026
 */
public final class MP3DurationUtil {

    private MP3DurationUtil() {
        // Prevent instantiation
    }

    /**
     * Gets the real duration of an MP3 file in milliseconds.
     * Reads through all frames to calculate total duration.
     *
     * @param filepath Path to the MP3 file
     * @return Duration in milliseconds, or -1 if error occurs
     */
    public static long getDuration(String filepath) {
        try (FileInputStream fis = new FileInputStream(filepath)) {
            Bitstream bitstream = new Bitstream(fis);
            long totalFrames = 0;
            float msPerFrame = 0;

            Header header = bitstream.readFrame();
            if (header == null) {
                return -1;
            }

            // Calculate milliseconds per frame from first header
            // Formula: (samples per frame / sample rate) * 1000
            msPerFrame = header.ms_per_frame();

            // Count all frames
            while (header != null) {
                totalFrames++;
                bitstream.closeFrame();
                header = bitstream.readFrame();
            }

            // Total duration = frames × ms per frame
            return (long) (totalFrames * msPerFrame);

        } catch (Exception e) {
            System.err.println("Error reading MP3 duration: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Gets the duration in seconds
     * @param filepath Path to the MP3 file
     * @return Duration in seconds, or -1 if error occurs
     */
    public static int getDurationInSeconds(String filepath) {
        long durationMs = getDuration(filepath);
        return durationMs > 0 ? (int) (durationMs / 1000) : -1;
    }

    /**
     * Formats duration as mm:ss string
     * @param filepath Path to the MP3 file
     * @return Formatted duration string, or "0:00" if error
     */
    public static String getDurationFormatted(String filepath) {
        int seconds = getDurationInSeconds(filepath);
        if (seconds < 0) {
            return "0:00";
        }

        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}

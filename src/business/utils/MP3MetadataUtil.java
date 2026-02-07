package business.utils;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Utility class for extracting metadata from MP3 files.
 * Uses basic ID3 tag parsing without external dependencies beyond JLayer.
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 06/02/2026
 */
public final class MP3MetadataUtil {

    private MP3MetadataUtil() {
        // Prevent instantiation
    }

    /**
     * Container class for MP3 metadata
     */
    public static class MP3Metadata {
        private String title;
        private String artist;
        private String album;
        private String genre;
        private boolean valid = false;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getArtist() { return artist; }
        public void setArtist(String artist) { this.artist = artist; }
        public String getAlbum() { return album; }
        public void setAlbum(String album) { this.album = album; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
    }

    /**
     * Extracts metadata from an MP3 file by reading ID3 tags
     * @param filepath Path to the MP3 file
     * @return MP3Metadata object containing extracted metadata
     */
    public static MP3Metadata extractMetadata(String filepath) {
        MP3Metadata metadata = new MP3Metadata();

        try {
            File file = new File(filepath);
            if (!file.exists()) {
                System.err.println("MP3 file not found: " + filepath);
                return metadata;
            }

            // Try to read ID3v2.3 or ID3v2.4 tags
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                byte[] header = new byte[10];
                raf.read(header);

                // Check for ID3 tag
                if (header[0] == 'I' && header[1] == 'D' && header[2] == '3') {
                    int version = header[3];
                    int size = ((header[6] & 0x7F) << 21) |
                              ((header[7] & 0x7F) << 14) |
                              ((header[8] & 0x7F) << 7) |
                              (header[9] & 0x7F);

                    byte[] tagData = new byte[size];
                    raf.read(tagData);

                    // Parse frames
                    parseID3v2Frames(tagData, metadata, version);
                }
            }

            // If no metadata from ID3v2, try ID3v1 at end of file
            if (!metadata.isValid()) {
                try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                    long fileSize = raf.length();
                    if (fileSize > 128) {
                        raf.seek(fileSize - 128);
                        byte[] id3v1 = new byte[128];
                        raf.read(id3v1);

                        if (id3v1[0] == 'T' && id3v1[1] == 'A' && id3v1[2] == 'G') {
                            String title = new String(id3v1, 3, 30, "ISO-8859-1").trim();
                            String artist = new String(id3v1, 33, 30, "ISO-8859-1").trim();
                            String album = new String(id3v1, 63, 30, "ISO-8859-1").trim();

                            if (!title.isEmpty()) metadata.setTitle(title);
                            if (!artist.isEmpty()) metadata.setArtist(artist);
                            if (!album.isEmpty()) metadata.setAlbum(album);

                            if (metadata.getTitle() != null && metadata.getArtist() != null) {
                                metadata.setValid(true);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error reading MP3 metadata: " + e.getMessage());
        }

        return metadata;
    }

    private static void parseID3v2Frames(byte[] data, MP3Metadata metadata, int version) {
        int pos = 0;
        int size = data.length;

        while (pos < size - 10) {
            // Read frame header
            String frameId = new String(data, pos, 4);
            int frameSize;

            if (version >= 3) {
                // ID3v2.3 and ID3v2.4
                frameSize = ((data[pos + 4] & 0xFF) << 24) |
                           ((data[pos + 5] & 0xFF) << 16) |
                           ((data[pos + 6] & 0xFF) << 8) |
                           (data[pos + 7] & 0xFF);
            } else {
                // ID3v2.2
                frameSize = ((data[pos + 3] & 0xFF) << 16) |
                           ((data[pos + 4] & 0xFF) << 8) |
                           (data[pos + 5] & 0xFF);
            }

            if (frameSize <= 0 || pos + 10 + frameSize > size) {
                break;
            }

            // Extract frame content (skip encoding byte)
            String content = "";
            if (frameSize > 1) {
                try {
                    content = new String(data, pos + 11, frameSize - 1, "UTF-8").trim();
                    if (content.isEmpty()) {
                        content = new String(data, pos + 11, frameSize - 1, "ISO-8859-1").trim();
                    }
                } catch (Exception e) {
                    // Ignore encoding errors
                }
            }

            // Map frame IDs to metadata fields
            switch (frameId) {
                case "TIT2": // Title
                    if (!content.isEmpty()) metadata.setTitle(content);
                    break;
                case "TPE1": // Lead performer(s)
                case "TPE2": // Band/orchestra/accompaniment
                    if (!content.isEmpty() && metadata.getArtist() == null) {
                        metadata.setArtist(content);
                    }
                    break;
                case "TALB": // Album
                    if (!content.isEmpty()) metadata.setAlbum(content);
                    break;
                case "TCON": // Genre
                    if (!content.isEmpty()) metadata.setGenre(content);
                    break;
            }

            // Check if we have enough data
            if (metadata.getTitle() != null && metadata.getArtist() != null) {
                metadata.setValid(true);
            }

            pos += 10 + frameSize;
        }
    }

    /**
     * Quick check if file has valid metadata
     * @param filepath Path to the MP3 file
     * @return true if metadata can be read
     */
    public static boolean hasMetadata(String filepath) {
        MP3Metadata metadata = extractMetadata(filepath);
        return metadata.isValid();
    }
}

package business;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * SongLyricsAPI Class used to retrieve the lyrics of a certain Song given its title and author
 *
 * @author Alan Beltrán
 * @version 1.0
 * @since 10-04-2022
 */
public class SongLyricsAPI {

    /**
     * Method that will return the json containing the lyrics of the song
     *
     * @param artist name of the artist
     * @param title  title of the song
     * @return Json with the lyrics of the requested song or null if not found
     */
    public String getLyricsJson(String artist, String title) {
        // Clean up artist name - remove featured artists and extra info
        String cleanArtist = artist.split("(?i)(ft\\.|feat\\.|featuring|with)")[0].trim();
        String cleanTitle = title.split("(?i)(\\(feat\\.|\\(ft\\.|\\(with)")[0].trim();
        
        // Remove parentheses and their contents from title
        cleanTitle = cleanTitle.replaceAll("\\s*\\([^\\)]*\\)", "").trim();
        
        try {
            String encodedArtist = URLEncoder.encode(cleanArtist, StandardCharsets.UTF_8);
            String encodedTitle = URLEncoder.encode(cleanTitle, StandardCharsets.UTF_8);
            
            String apiUrl = "https://api.lyrics.ovh/v1/" + encodedArtist + "/" + encodedTitle;
            System.out.println("Fetching lyrics from: " + apiUrl);
            
            URL urlObj = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Espotify/1.0");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            
            int responseCode = conn.getResponseCode();
            System.out.println("Lyrics API response code: " + responseCode);
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    String response = result.toString();
                    System.out.println("Lyrics API response: " + response.substring(0, Math.min(100, response.length())) + "...");
                    return response;
                }
            } else {
                System.err.println("Lyrics API error: HTTP " + responseCode);
                // Try alternative API
                return tryAlternativeLyricsAPI(cleanArtist, cleanTitle);
            }
        } catch (Exception e) {
            System.err.println("Error fetching lyrics: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    private String tryAlternativeLyricsAPI(String artist, String title) {
        try {
            // Try lrclib.net as fallback
            String encodedArtist = URLEncoder.encode(artist, StandardCharsets.UTF_8);
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
            
            String apiUrl = "https://lrclib.net/api/get?artist_name=" + encodedArtist + "&track_name=" + encodedTitle;
            System.out.println("Trying alternative API: " + apiUrl);
            
            URL urlObj = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Espotify/1.0");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    String response = result.toString();
                    // lrclib returns plain lyrics in "plainLyrics" field
                    if (response.contains("\"plainLyrics\"")) {
                        int start = response.indexOf("\"plainLyrics\":\"") + 16;
                        int end = response.indexOf("\"", start);
                        if (end > start) {
                            String lyrics = response.substring(start, end)
                                .replace("\\n", "\n")
                                .replace("\\r", "");
                            return "{\"lyrics\":\"" + lyrics + "\"}";
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Alternative lyrics API error: " + e.getMessage());
        }
        return null;
    }

}

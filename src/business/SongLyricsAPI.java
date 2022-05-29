package business;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * SongLyricsAPI Class used to retrieve the lyrics of a certain Song given its title and author
 *
 * @author Alan Beltrán, Álvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez
 * @version 1.0
 * @since 10-04-2022
 */
public class SongLyricsAPI {

    private String URL = "https://api.lyrics.ovh/v1/";
    private String charset = "UTF-8";

    /**
     * Method that will return the json containing the lyrics of the song
     *
     * @param artist name of the artist
     * @param title  title of the song
     * @return Json with the lyrics of the requested song or null if not found
     */
    public String getLyricsJson(String artist, String title) {
        artist = URLEncoder.encode(artist, StandardCharsets.UTF_8);
        title = URLEncoder.encode(title, StandardCharsets.UTF_8);
        if (artist.contains("ft")) {
            artist = artist.split("ft")[0].trim();
        }
        try {
            URL urlObj = new URL(URL + artist + "/" + title);

            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setConnectTimeout(15000);
            conn.connect();

            //Receive the response from the server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (IOException e) {
        }
        return null;
    }

}

package business;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SongLyricsAPI {

    private String URL = "https://api.lyrics.ovh/v1/";
    private String charset = "UTF-8";

    public String getLyricsJson(String artist,String title){
        artist = URLEncoder.encode(artist, StandardCharsets.UTF_8);
        title= URLEncoder.encode(title,StandardCharsets.UTF_8);
        try {
            URL urlObj = new URL(URL+artist+"/"+title);

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

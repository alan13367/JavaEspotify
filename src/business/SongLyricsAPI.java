package business;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SongLyricsAPI {

    private static String URL = "https://api.lyrics.ovh/v1/";
    private static String charset = "UTF-8";

    public static String getLyricsJson(String artist,String title){
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

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package mchien.code.helps;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public static String get(String urlStr) throws IOException {
        HttpURLConnection conn = null;

        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                return out.toString("UTF-8");
            } else {
                throw new IOException("HTTP GET Request Failed with Error code : " + responseCode);
            }
        } finally {
            if (out != null) out.close();
            if (in != null) in.close();
            if (conn != null) conn.disconnect();
        }
    }


}
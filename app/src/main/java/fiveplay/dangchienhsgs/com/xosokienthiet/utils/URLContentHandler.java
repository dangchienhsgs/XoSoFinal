package fiveplay.dangchienhsgs.com.xosokienthiet.utils;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dangchienhsgs on 9/8/14.
 */
public class URLContentHandler {
    public static String getURLContent(String... urls) {
        try {
            String result = "";
            for (int i = 0; i < urls.length; i++) {
                URL url = new URL(urls[i]);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                String str = "";
                while (scanner.hasNextLine()) {
                    str = str + scanner.nextLine();
                }
                result = result + str;
                scanner.close();
            }
            return result;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendGet(String url, String parameter)  {
        try{

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            // Send get request
            if (parameter!=null){
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(parameter);
                wr.flush();
                wr.close();
            }

            // Get response

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();


        } catch (IOException e){
            return null;
        }
    }

    // HTTP POST request
    public static String sendPost(String url, String urlParameters) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        // Read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

    public static void main(String args[]){
        System.out.println (sendGet("www.google.com",null));
    }
}
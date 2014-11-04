package fiveplay.dangchienhsgs.com.xosokienthiet.service;

/**
 * Created by dangchienhsgs on 04/11/2014.
 */

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ServiceUtilities {
    private static final String TAG="Server";

    /**
     *  Get all text from server in source
     *  Use for get JSON string from server
     * @param url: The inputted url
     * @return: String data
     */
    public static String getContentFromServer(String url) {
        try {
            // Open Connection
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();

            // Init Scanner
            Scanner scanner = new Scanner(inputStream);

            // Read data from server
            String result= "";
            while (scanner.hasNextLine()) {
                result = result + scanner.nextLine();
            }

            // End
            scanner.close();
            return result;

        } catch (IOException e) {
            Log.d(TAG, "The given url is fail");
            return null;
        }
    }

}

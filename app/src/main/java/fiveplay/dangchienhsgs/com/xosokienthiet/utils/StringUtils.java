package fiveplay.dangchienhsgs.com.xosokienthiet.utils;

import java.util.HashMap;

/**
 * Created by dangchienbn on 19/11/2014.
 */
public class StringUtils {
    public static final String[] goldCompanies = {
            "SJC", "PNJ", "PQJ", "BTMC", "VangTG(USD)"
    };

    public static final String[] moneyCountries = {
            "USD", "EUR", "GBP", "JPY", "AUD"
    };


    public static HashMap<String, String> analyzeGOLD(String[] arrayKey, String goldInfo) {

        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < arrayKey.length; i++) {

            String key = arrayKey[i];
            int startContentIndex = goldInfo.indexOf(key) + key.length() + 1;

            int endContentIndex = 0;
            if (i < arrayKey.length - 1) {
                endContentIndex = goldInfo.indexOf(arrayKey[i + 1]) - 1;
            }
            if (i == arrayKey.length - 1) {
                endContentIndex = goldInfo.length();
            }
            System.out.println(i + " " + startContentIndex + " " + endContentIndex);
            System.out.println(i + " " + key + " ");
            String content = goldInfo.substring(startContentIndex, endContentIndex);

            System.out.println(content);

            map.put(key, content);
        }

        return map;

    }

}

package fiveplay.dangchienhsgs.com.xosokienthiet.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dangchienbn on 12/11/2014.
 */
public class NguHanh {
    private final static String CONTENT = "MsgContents";
    private String content;

    public NguHanh(String content) {
        this.content = content;
    }

    public static NguHanh parse(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            String content = jsonObject.getString(CONTENT);

            return new NguHanh(content);

        } catch (JSONException e) {
            return null;
        }
    }
}

package fiveplay.dangchienhsgs.com.xosokienthiet.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LotteryResult {
    private String TAG = "LotteryResult";

    private int DEFAULT_NUMBER_PRIZE = 11;

    private List<String> prize;

    private List<String> lottoHeadTail;
    private List<String> lottoTailHead;

    private String datetime;

    public LotteryResult() {
        prize = new ArrayList<String>();
        lottoHeadTail = new ArrayList<String>();
        lottoTailHead = new ArrayList<String>();
    }


    public LotteryResult(String json) {
        analyze(json);
    }


    // Convert from jsonResult to LotteryResult
    private void analyze(String jsonResult) {
        try {

            JSONObject resultObj = new JSONObject(jsonResult);

            Log.d(TAG, jsonResult);
            // ketqua contains datetime and all prizes
            JSONObject ketqua = (JSONObject) resultObj.get("ketqua");


            // Get datetime from ketqua and set it to lottResult
            this.setDatetime(ketqua.getString("dateOpen"));

            // Read prizes from JSON
            prize = new ArrayList<String>();
            for (int i = 0; i < DEFAULT_NUMBER_PRIZE; i++) {
                String temp = ketqua.getString("prize" + i);

                if (temp != null) {
                    prize.add(temp);
                }
            }

            // Read lottoHeadTail from JSON
            lottoHeadTail = new ArrayList<String>();
            JSONArray array = ketqua.getJSONArray("lotto");

            for (int i = 0; i < array.length(); i++) {
                lottoHeadTail.add(array.get(i).toString());
            }

            // Convert lottoHeadTail to lottoTailHead
            lottoTailHead = lottoResultInverse(lottoHeadTail);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    /**
     * Convert from head-tail list to tail-head list or opposite
     *
     * @return the list of it's inverse
     */
    private List<String> lottoResultInverse(List<String> list) {
        if (list.size() == 10) {
            List<String> result = new ArrayList<String>();

            // Init the result list
            for (int i = 0; i < 10; i++) {
                result.add(i, "");
            }

            // Run i from 0 to 9
            for (int i = 0; i < list.size(); i++) {
                // Convert Ith element to array
                String value[] = list.get(i).split("-");

                // If digit in that array then result[digit] added by i
                for (String digit : value) {
                    int temp = Integer.parseInt(digit);

                    if (result.get(temp).isEmpty()) {
                        result.add(temp, String.valueOf(i));
                    } else {
                        result.add(temp, result.get(temp) + "," + i);
                    }
                }
            }

            return result;
        } else {
            Log.d(TAG, "Lotto Result Inverse fail because the list's size 's larger than 10");
            return null;
        }
    }


    public List<String> getPrize() {
        return prize;
    }

    public List<String> getLottoHeadTail() {
        return lottoHeadTail;
    }

    public List<String> getLottoTailHead() {
        return lottoTailHead;
    }
}

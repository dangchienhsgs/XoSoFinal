package fiveplay.dangchienhsgs.com.xosokienthiet.service;


import android.util.Log;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ServiceUtilities {
    private static final String TAG="Service Utilities";

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
            e.printStackTrace();
            return null;
        }
    }

    // HTTP POST request
    public static String sendPost(String url, String urlParameters){
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty( "Content-Type", "application/json");


            // Send post request
            if (urlParameters!=null){
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }

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

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private static JsonObject getData(String data){
        try{
            JsonParser jsonParser=new JsonParser();
            JsonObject jsonObject=(JsonObject) jsonParser.parse(data);
            return jsonObject;
        } catch (JsonIOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getVanTrinh(int day, int month, int year){
        String data="{\"MethodName\":\"GetNguHanhInfo\",\"ParamsList\":null,\"ApplicationId\":1,\"PlatformId\":3,\"Identifier\":\"619117091256443\",\"RequestTime\":\"20141104205744\",\"AppKey\":\"411537a8ae6d8a5b77bc335a6fd4f9ca\",\"AppChargeTypeId\":0,\"AppPlatformId\":21,\"AppVersion\":\"1_0_5\",\"BusinessPartnerId\":4,\"BusinessApplicationPlatformId\":16,\"CustomerId\":702795,\"FunctionId\":0}";
        String url="http://xoso24h.icsoft.vn/webservices/UtilityService.svc/"+day+"-"+month+"-"+year+"/GetBoiVanTrinhInfo";

        return sendPost(url, getData(data).toString());
    }

    public static String getNguHanh(int day, int month, int year){
        String data="{\"MethodName\":\"GetNguHanhInfo\",\"ParamsList\":null,\"ApplicationId\":1,\"PlatformId\":3,\"Identifier\":\"619117091256443\",\"RequestTime\":\"20141104205744\",\"AppKey\":\"411537a8ae6d8a5b77bc335a6fd4f9ca\",\"AppChargeTypeId\":0,\"AppPlatformId\":21,\"AppVersion\":\"1_0_5\",\"BusinessPartnerId\":4,\"BusinessApplicationPlatformId\":16,\"CustomerId\":702795,\"FunctionId\":0}";
        String url="http://xoso24h.icsoft.vn/webservices/UtilityService.svc/"+day+"-"+month+"-"+year+"/GetNguHanhInfo";

        return sendPost(url, getData(data).toString());
    }

    public static String getGiaVang(int day, int month, int year){
        String data="{\"MethodName\":\"GetNguHanhInfo\",\"ParamsList\":null,\"ApplicationId\":1,\"PlatformId\":3,\"Identifier\":\"619117091256443\",\"RequestTime\":\"20141104205744\",\"AppKey\":\"411537a8ae6d8a5b77bc335a6fd4f9ca\",\"AppChargeTypeId\":0,\"AppPlatformId\":21,\"AppVersion\":\"1_0_5\",\"BusinessPartnerId\":4,\"BusinessApplicationPlatformId\":16,\"CustomerId\":702795,\"FunctionId\":0}";
        String url="http://xoso24h.icsoft.vn/webservices/UtilityService.svc/"+day+month+year+"/GetGoldAndRateInfoByDate";

        return sendPost(url, getData(data).toString());
    }
    public static void main(String args[]){
        System.out.println (getVanTrinh(10, 10, 2013));
    }
}

package fiveplay.dangchienhsgs.com.xosokienthiet.utils;

public class URLBuilder {
    // List all urls which provide data from server
    public static final String URL_KET_QUA="http://play.dommedia.vn/appapi/api_xoso/get";
    public static final String URL_THONG_KE_TAN_SUAT="http://play.dommedia.vn/appapi/api_xoso/thongke_tansuat";
    public static final String URL_THONG_KE_IT_NHIEU="http://play.dommedia.vn/appapi/api_xoso/thongke_itnhieu";
    public static final String URL_THONG_KE_00_99="http://play.dommedia.vn/appapi/api_xoso/thongke_0099";
    public static final String URL_THONG_KE_LO_GAN="http://play.dommedia.vn/appapi/api_xoso/thongke_logan";


    private String url;

    private boolean isModified;

    /**
     * Constructor
     * @param original
     */
    public URLBuilder(String original){
        this.url=original;

        this.isModified=false;
    }



    /**
     * Add parameters for GET method in Url String
     * @param key: key value
     * @param value: value of key
     * @return the changed url
     */
    public URLBuilder append(String key, String value){
        if (isModified){
            url=url+"&"+key+"="+value;
            return this;
        } else {
            url=url+"?"+key+"="+value;
            isModified=true;
            return this;
        }
    }


    /**
     * Simple appending by add a string in the tail of url
     * @param str : appended string
     * @return: added url
     */
    public URLBuilder append(String str){
        url=url+str;
        return this;
    }


    /**
     * @return current url in String
     */
    public String create(){
        return url;
    }
}

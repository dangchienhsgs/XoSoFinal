package fiveplay.dangchienhsgs.com.xosokienthiet.model;

import org.json.JSONObject;

/**
 * Describe the data of Van Trinh Result when we clone it from internet
 */
public class VanTrinh {
    private static final String TUOI_AM_LICH="TuoiAmLich";
    private static final String VAN_TRINH_TONG_THE="VanTrinhTongThe";

    private String tuoiAmLich;
    private String vantrinhTongThe;

    public VanTrinh(String tuoiAmLich, String vantrinhTongThe) {
        this.tuoiAmLich = tuoiAmLich;
        this.vantrinhTongThe = vantrinhTongThe;
    }

    public static VanTrinh parse(String jsonString){
        try {
            JSONObject jsonObject=new JSONObject(jsonString);

            String tuoiAmLich=jsonObject.getString(TUOI_AM_LICH);
            String vantrinhTongThe=jsonObject.getString(VAN_TRINH_TONG_THE);

            return new VanTrinh(tuoiAmLich, vantrinhTongThe);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getTuoiAmLich() {
        return tuoiAmLich;
    }

    public String getVantrinhTongThe() {
        return vantrinhTongThe;
    }

    public void setTuoiAmLich(String tuoiAmLich) {
        this.tuoiAmLich = tuoiAmLich;
    }

    public void setVantrinhTongThe(String vantrinhTongThe) {
        this.vantrinhTongThe = vantrinhTongThe;
    }
}

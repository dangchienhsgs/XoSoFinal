package fiveplay.dangchienhsgs.com.xosokienthiet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dangchienhsgs on 05/11/2014.
 */
public class Common {
    public static final int NUMBER_DIGIT_LOTTO_VALUE=5;

    public static final String LOTTO_HOST[]={
            "Mien Bac",
            "Mien Nam",
            "Mien Trung"
    };

    public static final String LOTTO_HOST_ID[]={
            "mienbac",
            "mientrung",
            "hochiminh"
    };

    public static final String PRIZE_NAME[]={
            "prize0",
            "prize1",
            "prize2",
            "prize3",
            "prize4",
            "prize5",
            "prize6",
            "prize7",
    };
    public static final String LOCATION_CODE="code";
    public static final String DATE="date";

    public static final String[] DAY_IN_WEEK={
            "Thứ Hai",
            "Thứ Ba",
            "Thứ Tư",
            "Thứ Năm",
            "Thứ Sáu",
            "Thứ Bảy",
            "Chủ Nhật"
    };


    public static final String[] AREAS={
            "Mien Bac",
            "Mien Trung",
            "Mien Nam"
    };


    public static final String[] COMPANY_IN_MON_DAY={
            "Ha Noi ,Hai Phong",
            "Bac Ninh ,Lao Cai",
            "Bac Ninh,  Lao Cai",
    };

    public static final String[] COMPANY_IN_TUESDAY={
            "Ha Noi , Hai Phong",
            "Bac Ninh , Lao Cai",
            "Bac Ninh ,Lao Cai",
    };

    public static final String[] COMPANY_IN_WEDNESDAY={
            "Ha Noi \n Hai Phong",
            "Bac Ninh \n Lao Cai",
            "Bac Ninh \n Lao Cai",
    };

    public static final String[] COMPANY_IN_THURSDAY={
            "Ha Noi \n Hai Phong",
            "Bac Ninh \n Lao Cai",
            "Bac Ninh \n Lao Cai",
    };

    public static final String[] COMPANY_IN_FRIDAY={
            "Ha Noi , Hai Phong",
            "Bac Ninh , Lao Cai",
            "Bac Ninh , Lao Cai",
    };

    public static final String[] COMPANY_IN_SATURDAY={
            "Ha Noi \n Hai Phong",
            "Bac Ninh \n Lao Cai",
            "Bac Ninh \n Lao Cai",
    };

    public static final String[] COMPANY_IN_SUNDAY={
            "Ha Noi \n Hai Phong",
            "Bac Ninh \n Lao Cai",
            "Bac Ninh \n Lao Cai",
    };


    public static List<String> getDay() {
        return Arrays.asList(DAY_IN_WEEK);
    }

    public static HashMap<String, List<String>> getScheduleMap() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (int i = 0; i < DAY_IN_WEEK.length; i++) {
            switch (i) {
                case 0:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_MON_DAY));
                    break;
                case 1:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_TUESDAY));
                    break;
                case 2:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_WEDNESDAY));
                    break;
                case 3:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_THURSDAY));
                    break;
                case 4:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_FRIDAY));
                    break;
                case 5:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_SATURDAY));
                    break;
                case 6:
                    map.put(DAY_IN_WEEK[i], Arrays.asList(COMPANY_IN_SUNDAY));
                    break;
            }
        }

        return map;
    }

    public static void main(String args[]) {
        System.out.println(getScheduleMap().toString());
    }
}



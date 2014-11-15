package fiveplay.dangchienhsgs.com.xosokienthiet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dangchienhsgs on 05/11/2014.
 */
public class Common {
    public static final int NUMBER_DIGIT_LOTTO_VALUE=5;

    public static final String COMPANIES_IN_NORTH[] = {
            "Hà Nội",
            "Hải Phòng",
            "Nam Định",
            "Thái Bình",
            "Quảng Ninh",
            "Bắc Ninh",
    };

    public static final String COMPANIES_IN_NORTH_ID[] = {
            "mienbac",
            "haiphong",
            "namdinh",
            "thaibinh",
            "quangninh",
            "bacninh"
    };

    public static final String[] COMPANIES_IN_MIDDLE = {
            "Đà Nẵng",
            "Khánh Hòa",
            "Đắk Lắk",
            "Quảng Nam",
            "Phú Yên",
            "Huế",
            "Khánh Hòa",
            "Kon Tum",
            "Đắk Nông",
            "Quảng Ngãi",
            "Gia Lai",
            "Ninh Thuận",
            "Bình Định",
            "Quảng Bình",
            "Quảng Trị"
    };

    public static final String[] COMPANIES_IN_MIDDLE_ID = {
            "danang",
            "khanhhoa",
            "daklak",
            "quangnam",
            "phuyen",
            "hue",
            "khanhhoa",
            "kontum",
            "daknong",
            "quangngai",
            "gialai",
            "ninhthuan",
            "binhdinh",
            "quangbinh",
            "quangtri"
    };


    public static final String[] COMPANIES_IN_SOUTH = {
            "TP.HCM",
            "Cần Thơ",
            "Đồng Nai",
            "Sóc Trăng",
            "Bạc Liêu",
            "Bến Tre",
            "Vũng Tàu",
            "Cà Mau",
            "Đồng Tháp",
            "Đà Lạt",
            "Kiên Giang",
            "Tiền Giang",
            "Bình Phước",
            "Hậu Giang",
            "Long An",
            "Bình Dương",
            "Trà Vinh",
            "Vĩnh Long",
            "An Giang",
            "Bình Thuận",
            "Tây Ninh"
    };

    public static final String[] COMPANIES_IN_SOUTH_ID = {
            "hochiminh",
            "cantho",
            "dongnai",
            "soctrang",
            "baclieu",
            "bentre",
            "vungtau",
            "camau",
            "dongthap",
            "dalat",
            "kiengiang",
            "tienggiang",
            "binhphuoc",
            "haugiang",
            "longan",
            "binhduong",
            "travinh",
            "vinhlong",
            "angiang",
            "binhthuan",
            "tayninh"

    };


    public static final String PRIZE_NAME[]={
            "Giải đặc biệt",
            "Giải nhất",
            "Giải nhì",
            "Giải ba",
            "Giải tư",
            "Giải năm",
            "Giải sáu",
            "Giải b",
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


    public static final String[] UTILITIES = {
            "Chơi thử",
            "Giá vàng",
            "Xem Vận Trình",
            "Xem Ngũ Hành",
            "Truyện Cười",
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



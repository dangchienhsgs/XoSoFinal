package fiveplay.dangchienhsgs.com.xosokienthiet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dangchienhsgs on 05/11/2014.
 */
public class Common {
    public static final int NUMBER_DIGIT_LOTTO_VALUE = 5;


    public static final int INDEX_RESULT_FRAGMENT = 0;
    public static final int INDEX_STATISTIC_FRAGMENT = 1;
    public static final int INDEX_SCHEDULE_FRAGMENT = 2;
    public static final int INDEX_UTILITIES_FRAGMENT = 3;
    public static final int INDEX_DREAM_BOOK_FRAGMENT = 4;
    public static final int INDEX_TRY_PLAY_FRAGMENT = 5;
    public static final int INDEX_GOLD_PRICE_FRAGMENT = 6;
    public static final int INDEX_VAN_TRINH_FRAGMENT = 7;
    public static final int INDEX_NGU_HANH_FRAGMENT = 8;
    public static final int INDEX_FUN_STORY_FRAGMENT = 9;


    public static final String KEY_NGU_HANH_YEAR = "ngu_hanh_year";
    public static final String KEY_NGU_HANH_MONTH = "ngu_hanh_month";
    public static final String KEY_NGU_HANH_DAY = "ngu_hanh_day";

    public static final String KEY_VAN_TRINH_YEAR = "key_van_trinh_year";
    public static final String KEY_VAN_TRINH_MONTH = "key_van_trinh_month";
    public static final String KEY_VAN_TRINH_DAY = "key_van_trinh_day";


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
            "mienbac",
            "mienbac",
            "mienbac",
            "mienbac",
            "mienbac"
    };
    public static final String[] COMPANIES = {
            "Hà Nội",
            "Hải Phòng",
            "Nam Định",
            "Thái Bình",
            "Quảng Ninh",
            "Bắc Ninh",
            "Đà Nẵng",
            "Khánh Hòa",
            "Đắk Lắk",
            "Quảng Nam",
            "Phú Yên",
            "Thừa Thiên Huế",
            "Khánh Hòa",
            "Kon Tum",
            "Đắk Nông",
            "Quảng Ngãi",
            "Gia Lai",
            "Ninh Thuận",
            "Bình Định",
            "Quảng Bình",
            "Quảng Trị",
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

    public static final String[] COMPANIES_IN_MIDDLE = {
            "Đà Nẵng",
            "Khánh Hòa",
            "Đắk Lắk",
            "Quảng Nam",
            "Phú Yên",
            "Thừa Thiên Huế",
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
            "thuathienhue",
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
            "tiengiang",
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


    public static final String PRIZE_NAME[] = {
            "Giải đặc biệt",
            "Giải nhất",
            "Giải nhì",
            "Giải ba",
            "Giải tư",
            "Giải năm",
            "Giải sáu",
            "Giải bảy",
    };

    public static final String RANGE = "range";
    public static final String LOCATION_CODE = "code";
    public static final String DATE = "date";

    public static final String[] DAY_IN_WEEK = {
            "Thứ Hai",
            "Thứ Ba",
            "Thứ Tư",
            "Thứ Năm",
            "Thứ Sáu",
            "Thứ Bảy",
            "Chủ Nhật"
    };

    public static final String[] DIGITS = {
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
    };

    public static final String[] AREAS = {
            "Miền Bắc",
            "Miền Trung",
            "Miền Nam"
    };


    public static final String[] COMPANY_IN_MON_DAY = {
            "Hà Nội",
            "Huế\nPhú Yên",
            "Đồng Tháp \nTP Hồ Chí Minh",
    };

    public static final String[] COMPANY_IN_TUESDAY = {
            "Quảng Ninh",
            "Quảng Nam \nĐắk Lắk",
            "Vũng Tàu \nBến Tre \nBạc Liêu"
    };

    public static final String[] COMPANY_IN_WEDNESDAY = {
            "Bắc Ninh",
            "Khánh Hòa \nĐà Nẵng",
            "Cần Thơ \nSóc Trăng \nĐồng Nai",
    };
    public static final String[] COMPANY_IN_THURSDAY = {
            "Hà Nội",
            "Bình Định \nQuảng Bình \nQuảng Trị",
            "An Giang \nTây Ninh \nBình Thuận"
    };

    public static final String[] COMPANY_IN_FRIDAY = {
            "Hải Phòng",
            "Vĩnh Long \nBình Dương \nTrà Vinh",
            "Ninh Thuận \nGia Lai",
    };

    public static final String[] COMPANY_IN_SATURDAY = {
            "Nam Định",
            "Quảng Ngãi \nĐăk Nông \nĐà Nẵng",
            "Long An \nBình Phước \nHậu Giang \nTP Hồ Chí Minh",
    };

    public static final String[] COMPANY_IN_SUNDAY = {
            "Thái Bình",
            "Kon Tum \nKhánh Hòa",
            "Kiên Giang \nTiền Giang \nĐà Lạt",
    };


    public static final String[] UTILITIES = {
            "Sổ mơ",
            "Chơi thử",
            "Giá vàng",
            "Xem Vận Trình",
            "Xem Ngũ Hành",
            "Truyện Cười",
    };

    public static final String[] SCHEDULE_TYPES = {
            "Thống kê logan",
            "Thống kê 00-99",
            "Thống kê ít nhiều",
            "Thống kê xuất hiện"
    };

    public static String[] DEFAULT_NUM_TIMES = {
            "30", "60", "100", "Số khác"
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



package pay.account.coupon.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Coupon 번호 생성
 * 형태: XXXXX-XXXXXX-XXXXXXXX
 */
public class CouponCreateUtil {
    private static final char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private CouponCreateUtil() {
    }

    public static String createRandomNum(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS[random.nextInt(CHARS.length)]);
        }
        String couponStr = sb.toString();
        String first = couponStr.substring(0, 5) + "-";
        String second = couponStr.substring(5, 11) + '-';
        String third = couponStr.substring(11, length);

        return first + second + third;
    }

    public static String createDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    public static String expireDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, 3);
        return df.format(cal.getTime());
    }


}

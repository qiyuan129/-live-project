package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phoneNumValidator {
    //手机号码验证规则
    private static Pattern CHINESE_PHONE_PATTERN = Pattern.compile("((13|15|17|18)\\d{9})|(14[57]\\d{8})");
    /**
     * 是否是有效的中国手机号码
     * @param phone 电话号码
     * @return
     */
    public static boolean Validate(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }

        Matcher matcher = CHINESE_PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }
}

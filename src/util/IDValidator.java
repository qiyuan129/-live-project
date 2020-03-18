package util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDValidator {
    public static boolean Validate(String IDStr) {
        String Ai = "";//对身份证号码进行分析
        //判断是否为空
        if(null == IDStr || IDStr.trim().isEmpty())
            return false;
        // 判断号码的长度 15位或18位
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            return false;
        }
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (!isNumeric(Ai)) {
            //tipInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // 判断出生年月是否有效
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 日期
        if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
            //tipInfo = "身份证出生日期无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            return false;
        }
        // 判断地区码是否有效
        ArrayList<String> areacode=areaCode();
        // 如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areacode.indexOf(Ai.substring(0, 2)) == -1) {
            return false;
        }
        if (!isVarifyCode(Ai, IDStr)) {
            return false;
        }
        return true;
    }
    /**
     * 判断第18位校验码是否正确 第18位校验码的计算方式： 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
     * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2 2. 用11对计算结果取模 Y = mod(S, 11) 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     */
    private static boolean isVarifyCode(String Ai, String IDStr) {
        String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (IDStr.length() == 18) {
            if (!Ai.equals(IDStr)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     *
     * @param strnum
     * @return
     */
    private static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(strnum);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @param strDate
     * @return boolean
     */
    private static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回一个含有省份代码的ArrayList
     * @return
     */
    private static ArrayList<String> areaCode(){
        ArrayList<String> areaCodes=new ArrayList<>();
        areaCodes.add("11");
        areaCodes.add("12");
        areaCodes.add("13");
        areaCodes.add("14");
        areaCodes.add("15");
        areaCodes.add("21");
        areaCodes.add("22");
        areaCodes.add("23");
        areaCodes.add("31");
        areaCodes.add("32");
        areaCodes.add("33");
        areaCodes.add("34");
        areaCodes.add("35");
        areaCodes.add("36");
        areaCodes.add("37");
        areaCodes.add("41");
        areaCodes.add("42");
        areaCodes.add("43");
        areaCodes.add("44");
        areaCodes.add("45");
        areaCodes.add("46");
        areaCodes.add("50");
        areaCodes.add("51");
        areaCodes.add("52");
        areaCodes.add("53");
        areaCodes.add("54");
        areaCodes.add("61");
        areaCodes.add("62");
        areaCodes.add("63");
        areaCodes.add("64");
        areaCodes.add("65");
        areaCodes.add("71");
        areaCodes.add("81");
        areaCodes.add("82");
        areaCodes.add("91");

        return areaCodes;
    }
}

package service;

import util.IDValidator;
import util.phoneNumValidator;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:02 下午
 * @Version 1.0
 */
public class UserService {
    public static boolean isIDValidator(String identity){
        if(IDValidator.Validate(identity)){
            return true;
        }
        return false;
    }

    public static boolean isTruePhone(String phone){
        if (phoneNumValidator.Validate(phone)){
            return false;
        }
        return true;
    }
}

package FrontEnd;

import FrontEnd.IRegisterService;

/***简单实现接口，方便前端测试使用
 *
 */
public class RegistTestClass implements IRegisterService
{
    /*** 测试用接口实现，注意替换。将用户预约登记的信息提交
     * @param userName 用户真实姓名
     * @param userIdentify 用户身份证号
     * @param userPhone 用户手机号
     * @param num 用户预约的口罩数量
     */
    @Override
    public int sendMessage(String userName, String userIdentify, String userPhone, int num)
    {
        return 123456;
    }
}

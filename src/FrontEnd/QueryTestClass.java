package FrontEnd;

import java.util.ArrayList;
import FrontEnd.IRegisterService;
public class QueryTestClass implements IQueryService {
    /***测试用接口实现，注意替换.查询是否中签，若中签，给出购买凭证
     * @param registID 预约ID
     * */

    @Override
    public ArrayList<Object> isExistSection(int registID)
    {
        ArrayList<Object> arrayList= new ArrayList<>();
        arrayList.add("123456");
        arrayList.add("123456");
        arrayList.add("123456");
        arrayList.add(123456);
        return arrayList;
    }
}

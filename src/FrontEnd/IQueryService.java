import java.util.ArrayList;


public interface IQueryService
{
    /***测试用接口，注意替换.查询是否中签，若中签，给出购买凭证
 * @param registID 预约ID
 * */
    public ArrayList<Object> isExistSection(int registID);

}

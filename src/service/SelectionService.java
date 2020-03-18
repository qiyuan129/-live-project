package service;

import dao.AppointmentDaoImpl;
import dao.RegisterDaoImpl;
import dao.SelectionDaoImpl;
import model.Appointment;
import model.Register;
import model.Selection;

import java.util.ArrayList;

/**
 * @ClassName SelectionService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:03 下午
 * @Version 1.0
 */
public class SelectionService  {
    /**
     * 产生中签
     */
    public void createSelection(){
        AppointmentDaoImpl appointDao = new AppointmentDaoImpl();//获取一个AppointmentDaoImpl对象
        //得到最新预约对象，里面包含预约时间段等数据
        Appointment lastAppointment =  appointDao.getLastAppointment();
        RegisterDaoImpl registerDao = new RegisterDaoImpl();
        //获取候选者列表 条件 register.预约表id= lastAppointment.id
        ArrayList<Register> candidate= registerDao.getByAppointmentID(lastAppointment.getId());
        SelectionDaoImpl selectDao = new SelectionDaoImpl();
        //总的口罩数量
        int totalRespirator = lastAppointment.getMask();
        ArrayList<Selection> selected = new  ArrayList<Selection>();
        int cnt=0;
        for(int i=0;i < candidate.size()&& cnt < totalRespirator;i++){
            //用户申请的口罩数量要小于规定数量，否则直接跳过这个用户
            if(candidate.get(i).getMask()<=lastAppointment.getMaskMAX()){
                cnt+=candidate.get(i).getMask();//口罩数量足够才能预约
                if(cnt<=totalRespirator){
                    Selection selItem = new Selection();
                    selItem.setUserID(candidate.get(i).getUserID());//userID
                    selItem.setRegisterID(candidate.get(i).getId());//登记表id
                    selItem.setTime(candidate.get(i).getTime());
                    selItem.setAppointmentID(candidate.get(i).getAppointment());//AppointmentID
                    selected.add(selItem);
                }
            }

        }
        selectDao.addSelections(selected);//写入数据库
    }

}

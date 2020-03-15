package model;

import java.util.Date;

/**
 * @ClassName appointment
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:58 下午
 * @Version 1.0
 */

public class Appointment {
	
	    int id=0;
	    Date start;
	    Date end;
	    int mask;
	    int maskMAX;
	    
	    public Appointment(int id,Date s,Date e,int mask,int maskMAX){
	    	this.id=id;
	    	this.start=s;
	    	this.end=e;
	    	this.mask=mask;
	    	this.maskMAX=maskMAX;
	    }
	    
	    public int getId() {
			return id;
		}
	    
	    public void setStart(Date s) {
	    	start=s;
		}
	    
	    public Date getStart() {
			return start;
		}
	    
	    public void setEnd(Date e) {
	    	end=e;
		}
	    
	    public Date getEnd() {
			return end;
		}
	    
	    public void setMask(int m) {
			mask=m;
		}
	    
	    public int getMask() {
			return mask;
		}
	    
	    public void setMaskMAX(int m) {
			maskMAX=m;
		}
	    
	    public int getMaskMAX() {
			return maskMAX;
		}
	    
	    
	
}

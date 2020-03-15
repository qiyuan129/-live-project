package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName appointment
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:58 下午
 * @Version 1.0
 */
public class Appointment {
    int id;
    Date start;
    Date end;
    int mask;
    int maskMAX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getMaskMAX() {
        return maskMAX;
    }

    public void setMaskMAX(int maskMAX) {
        this.maskMAX = maskMAX;
    }
}

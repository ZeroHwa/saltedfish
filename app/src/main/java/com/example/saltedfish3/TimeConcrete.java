package com.example.saltedfish3;



import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.crud.LitePalSupport;

/**
 *具体有用功、无用功时间表
 */

/**
 * @author by Zero_Hwa,
 * @blog https://blog.csdn.net/Zero_HWA
 * @date on 2019/9/24.
 * Do your best in the process and have a clear conscience in the end
 * PS: Not easy to write code, please indicate.
 */

public class TimeConcrete extends LitePalSupport {
    public final static int ADVANTAGE = 1;
    public final static int DISADVANTAGE = 0;

    /**
     * 日期
     */
    private String date;
    /**
     * 备注
     */
    private String remark;
    /**
     * 标识符，为1的时候为有用功。为0的时候为无用功
     */
    private int tag;
    /**
     * 时长
     */
    private long time;

    private Diary diary;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}

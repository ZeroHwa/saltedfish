package com.example.saltedfish3;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author by Zero_Hwa,
 * @blog https://blog.csdn.net/Zero_HWA
 * @date on 2019/9/24.
 * Do your best in the process and have a clear conscience in the end
 * PS: Not easy to write code, please indicate.
 * 今日任务 数据库
 */
public class Mission extends LitePalSupport {

    @Column(unique = true,nullable = false)
    private int id;

    private String mission;

    private boolean complete;

    private Diary diary;

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

package com.example.saltedfish3;


/**
 * @author by Zero_Hwa,
 * @blog https://blog.csdn.net/Zero_HWA
 * @date on 2019/9/24.
 * Do your best in the process and have a clear conscience in the end
 * PS: Not easy to write code, please indicate.
 */
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/*
日记数据库
包含日期，有用功时间，无用功时间，今日总结以及今日任务。
 */

public class Diary extends LitePalSupport {
    @Column(nullable = false)
    private String date;
    private Long advantageTime;
    private Long disadvantageTime;
    private String todaySummrize;
    private List<Mission> missions = new ArrayList<>();
    private List<TimeConcrete> timeConcretes = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAdvantageTime() {
        return advantageTime;
    }

    public void setAdvantageTime(Long advantageTime) {
        this.advantageTime = advantageTime;
    }

    public Long getDisadvantageTime() {
        return disadvantageTime;
    }

    public void setDisadvantageTime(Long disadvantageTime) {
        this.disadvantageTime = disadvantageTime;
    }

    public String getTodaySummrize() {
        return todaySummrize;
    }

    public void setTodaySummrize(String todaySummrize) {
        this.todaySummrize = todaySummrize;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}

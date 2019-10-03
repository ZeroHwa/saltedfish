package com.example.saltedfish3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author by Zero_Hwa,
 * @blog https://blog.csdn.net/Zero_HWA
 * @date on 2019/9/24.
 * Do your best in the process and have a clear conscience in the end
 * PS: Not easy to write code, please indicate.
 * 看名字就知道是签到模块啦
 */
public class SignIn {
    private static int currentDay;
//    private static boolean sign_or_not;
    /**
     * 已经签到
     */
    final static boolean SIGNINED  = false;
    /**
     * 还没签到
     */
    final static boolean SIGNNOT = true;
    static SimpleDateFormat ftday = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat fthour = new SimpleDateFormat("HH:mm:ss");

//    private static SharedPreference

     static boolean signJudge(){
         List<Diary> diaries = LitePal.where("date = ?",ftday.format(new Date()))
                 .find(Diary.class);
         if (diaries == null || diaries.size() ==0){
             Diary diary = new Diary();
             diary.setDate(ftday.format(new Date()));
             diary.save();
             return SIGNNOT;
         }else{
             return SIGNINED;
         }
    }
    static String popDialog(boolean sign){
         Log.v("SignIn","haven't build dialog");

//         下处错误，以为Dialog依赖于Activity，不能用getContext
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.mact);
        Log.v("SignIn","Build dialog success");


        dialog.setTitle("今日签到");
         if(sign == SIGNINED){
             dialog.setMessage("不好意思，你今天已经签过到了");
             dialog.setCancelable(true);
             dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             Log.v("SignIn","before  signined dialog show");
             dialog.show();
             Log.v("SignIn","after signined dialog show");
             return null;
         }else if(sign == SIGNNOT){
             dialog.setMessage("现在是"+fthour.format(new Date())+"\n 您已成功签到！");
             dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             dialog.show();
             Log.v("SignIn","before  signnot dialog show");
             return ReadAphorism.radomAphorism();
         }
         return "defeated_in_sign_in";
    }
}

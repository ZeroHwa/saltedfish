package com.example.saltedfish3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/*
获取context用，方便在任何一个类Toast，但是在main里设置了一个静态变量指向MainActity了，这个似乎就没什么用了。。
 */

 /**
  * @author by Zero_Hwa,
  * @blog https://blog.csdn.net/Zero_HWA
  * @date on 2019/9/24.
  * Do your best in the process and have a clear conscience in the end
  * PS: Not easy to write code, please indicate.
  */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }
    public static Context getContext(){
        return context;
    }
//    public static Activity getActivity(){
//        return (Activity)context;
//    }
}

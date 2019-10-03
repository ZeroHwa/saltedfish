package com.example.saltedfish3;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
随机读取一行名言，一行一行读，读到空白行就跳过，有东西就返回出来
 */


 /**
  * @author by Zero_Hwa,
  * @blog https://blog.csdn.net/Zero_HWA
  * @date on 2019/9/24.
  * Do your best in the process and have a clear conscience in the end
  * PS: Not easy to write code, please indicate.
  */


public class ReadAphorism {
    static List<String> aphorism = new ArrayList<>();
    static void readAphorism(Context context){
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = context.getAssets().open("aphorism.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null){
                if (line.isEmpty()||" ".equals(line)){
                    continue;
                }
                aphorism.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    static String radomAphorism(){
        Random random=new Random();
        int i = random.nextInt(aphorism.size());
        return aphorism.get(i);
    }
}

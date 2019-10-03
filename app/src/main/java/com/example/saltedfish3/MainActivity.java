package com.example.saltedfish3;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @author by Zero_Hwa,
 * @blog https://blog.csdn.net/Zero_HWA
 * @date on 2019/9/24.
 * Do your best in the process and have a clear conscience in the end
 * PS: Not easy to write code, please indicate.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    public static MainActivity mact;

    /**
     * 有用功无用功变量及常量
     */
    Button advOrNotBtn;
    public final static int ADVANTAGE_BTN = 1;
    public final static int DISADVANTAGE_BTN = 0;
    public static int advOrNot = ADVANTAGE_BTN;

    /**
     * 计时
     */
    public final static String START_COUNT = "开始计时";
    public final static String STOP_COUNT = "停止计时";
    Button startCount;

    /**
     * 计时器
     */
    Chronometer ch;
    EditText remarkText;
    public long time;
    public String remark;

    /**
     *日期
     */
    static SimpleDateFormat ftday = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat fthour = new SimpleDateFormat("HH-mm-ss");


    /**
     * 签到变量
     */
    ImageButton signIn;

    /**
     * 名言
     */
    TextView saying;
    static String mingyan;
    private SharedPreferences pref;
    private  SharedPreferences.Editor editor;


    /**
     * 有用功vs无用功
     */
    mPre adVsDis;


    /**
     * 今日任务
     */
    Button addTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.content_main);

        //初始化
        init();

        //如果之前保存有名言，则打出到textview里
        if(savedInstanceState != null){
            mingyan = savedInstanceState.getString("mingyan");
            saying.setText(mingyan);
        }else{
            saying.setText(pref.getString("mingyan","今日名言"));
        }


        //方便在其他方法启动Toast或者Dialog
        mact = this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //浮窗
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //点击签到
        signIn.setOnClickListener(this);



        //选择有用功或无用功
        advOrNotBtn = findViewById(R.id.adv_or_not_btn);
        advOrNotBtn.setOnClickListener(this);

        //计时器
        startCount.setOnClickListener(this);
    }



/**
 *选择有用功或者无用功，有用功则把全局变量 advOrNot 设置为ADVANTAGE_BTN，否则设置为DISADVANTAGE_BTN
 */
    private void showpopmenu(View view) {
        PopupMenu menu = new PopupMenu(this,view);
        menu.getMenuInflater().inflate(R.menu.adv_or_not,menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.advantage_item:
                        Toast.makeText(MainActivity.this, "有用功", Toast.LENGTH_SHORT).show();
                        advOrNotBtn.setText("有用功");
                        advOrNotBtn.setBackgroundColor(Color.GREEN);
                        advOrNot = ADVANTAGE_BTN;
                        break;

                    case R.id.disadvantage_item:
                        Toast.makeText(MainActivity.this, "无用功", Toast.LENGTH_SHORT).show();
                        advOrNotBtn.setText("无用功");
                        advOrNotBtn.setBackgroundColor(Color.RED);
                        advOrNot = DISADVANTAGE_BTN;
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        menu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });
        menu.show();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_summarize) {
            // Handle the camera action
        } else if (id == R.id.nav_help) {
            Log.e("MainActivity","click help");

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signIn:
                mingyan = SignIn.popDialog(SignIn.signJudge());
                if(mingyan != null){
                    saying.setText(mingyan);
                    editor.putString("mingyan",mingyan);
                    editor.apply();
                }
                break;
            case R.id.start_count_btn:
                actionCount();
                break;
            case R.id.adv_or_not_btn:
                showpopmenu(v);
                break;
            default:
                break;
        }

    }


    /**
     * 保存名言
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = mingyan;
        outState.putString("mingyan",tempData);
    }


    public void actionCount(){

        if(startCount.getText().equals(START_COUNT)){
            Toast.makeText(this, startCount.getText(), Toast.LENGTH_SHORT).show();
            ch.setBase(SystemClock.elapsedRealtime());
            ch.start();

            //选择有用功or无用功使能false
            advOrNotBtn.setClickable(false);
            startCount.setText(STOP_COUNT);
        }else if (startCount.getText().equals(STOP_COUNT)){
            ch.stop();
            time = SystemClock.elapsedRealtime() - ch.getBase();
            remark = remarkText.getText().toString();
            Toast.makeText(this, startCount.getText(), Toast.LENGTH_SHORT).show();
            String str;
            if(advOrNot == ADVANTAGE_BTN){
                str = "有用功";
            }else{
                str = "无用功";
            }
            Toast.makeText(this, "此次"+str+"时长为"+time/60000+"分"+time%60000/1000+"秒", Toast.LENGTH_SHORT).show();
            doneThings();

            //清空备注
            remarkText.setText("");
            ch.setBase(SystemClock.elapsedRealtime());
            startCount.setText(START_COUNT);

            //选择有用功or无用功使能true
            advOrNotBtn.setClickable(true);

            //显示进度条

            float[] zuoVsyou = new float[2];
            Log.e("MainActivity","before adVsDisadv");
            zuoVsyou = adVsDisadv();
            Log.e("MainActivity","after adVsDisadv");
            Toast.makeText(this, "有用功"+zuoVsyou[0]+"无用功"+zuoVsyou[1], Toast.LENGTH_SHORT).show();
            adVsDis.setiNum((float)zuoVsyou[0]);
            adVsDis.setoNum((float)zuoVsyou[1]);
        }
    }

    /**
     *创建对应的表及项
     */
    public void doneThings(){
        TimeConcrete thisThing = new TimeConcrete();
        thisThing.setDate(ftday.format(new Date()));
        thisThing.setTime(time);
        if (advOrNot == ADVANTAGE_BTN){
            thisThing.setTag(TimeConcrete.ADVANTAGE);
        }else if (advOrNot == DISADVANTAGE_BTN){
            thisThing.setTag(TimeConcrete.DISADVANTAGE);
        }
        thisThing.setRemark(remark);
        thisThing.save();
    }

    /**
     *显示进度条，返回有用功及无用功的时间
     */
    public float[] adVsDisadv(){
//        TimeConcrete timeConcrete = new TimeConcrete();
        long advTime = 0;
        long disadvTime = 0;
        List<TimeConcrete> timeConcretes = LitePal.select("time","tag","date").where("date=?",ftday.format(new Date())).find(TimeConcrete.class);
        Log.e("MainActivity","长度"+timeConcretes.size());
        for(int i = 0;i<timeConcretes.size();i++){
            if(timeConcretes.get(i).getTag() == TimeConcrete.ADVANTAGE){
                advTime = timeConcretes.get(i).getTime() + advTime;
                Log.e("MainActivity","advTimes"+i+timeConcretes.get(i).getTime());
            }else if(timeConcretes.get(i).getTag() == TimeConcrete.DISADVANTAGE){
                disadvTime = disadvTime + timeConcretes.get(i).getTime();
                Log.e("MainActivity","disadvTimes"+i+timeConcretes.get(i).getTime());
            }
        }
        float[] vsTime = new float[2];
        vsTime[0] = (float) advTime/1000;
        vsTime[1] = (float) disadvTime/1000;
        return vsTime;
    }


    /**
     *初始化模块
     */
    public void init(){
        Log.e("MainActivity","before init");

        //创建数据库表，如果有了就不会创建了
        Connector.getDatabase();

        //将名言读入缓存中
        ReadAphorism.readAphorism(this);

//        绑定签到建
        signIn = findViewById(R.id.btn_signIn);

        //绑定名言textview
        saying = findViewById(R.id.Saying);
//
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        //绑定计时按钮、计时器、备注
        startCount = findViewById(R.id.start_count_btn);
        ch = findViewById(R.id.chronometer);
        remarkText = findViewById(R.id.remark_text);

        //绑定百分比条
        adVsDis = findViewById(R.id.myPre);

        //绑定新建任务
        addTask = findViewById(R.id.add_task);
    }

}

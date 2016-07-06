package com.moying.a3dgamedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.moying.a3dgamedemo.service.DownLoadService;
import com.moying.a3dgamedemo.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends AppCompatActivity {
   private GifImageView gifImageView;
    private Animation animation;
    //判断网络是否打开
    boolean netOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gifImageView= (GifImageView) findViewById(R.id.activity_welcome_imageshow);

        animation=new AlphaAnimation(0,1.0f);
        animation.setDuration(3000);
        gifImageView.setAnimation(animation);
        //给动画添加一个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //判断网络
                netOpen= NetUtils.netConnect(WelcomeActivity.this);
                if (netOpen){
                    //开启service，下载数据
                    Intent downloadserviceIntent=new Intent(WelcomeActivity.this, DownLoadService.class);
                    startService(downloadserviceIntent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            if (!netOpen){
                Toast.makeText(WelcomeActivity.this,"请连接网络",Toast.LENGTH_LONG).show();
            }
                isFirstLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
//判断是否第一次登录
    private void isFirstLogin() {
        //创建sharedPreferences对象
        SharedPreferences sharedPreferences=getSharedPreferences("isFirstLogin", Context.MODE_PRIVATE);
        //获得sharedPreferences对象中的isLogin属性
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        if (!isLogin){
            Log.i("aaa","第一次登录");
            Intent guidentIntent = new Intent(WelcomeActivity.this,GuideActivity.class);
            startActivity(guidentIntent);
            finish();
;
        }else{
            Log.i("aaa","第2次登录");
            Intent mainIntent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

    }
}

package com.cblue.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.cblue.a3dgame.service.DownLoadService;
import com.cblue.a3dgame.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

/**
 * 欢迎界面
 * 设置一个动画
 */
public class WelcomActivity extends AppCompatActivity {

    GifImageView gifImageView;
    Animation animation;
    //判断网络是否打开
    boolean netOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        gifImageView = (GifImageView)findViewById(R.id.welcome_git);
        //添加一个动画
        animation = new AlphaAnimation(0,1.0f);
        animation.setDuration(3000);
        gifImageView.startAnimation(animation);
        //给动画添加一个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //判断网络
                netOpen =  NetUtils.netConnect(WelcomActivity.this);
                if(netOpen) {
                    //开始Service，下载数据
                    Intent downloadServiceIntent = new Intent(WelcomActivity.this, DownLoadService.class);
                    startService(downloadServiceIntent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!netOpen){
                    Toast.makeText(WelcomActivity.this,"请连接你的网络",Toast.LENGTH_LONG).show();
                }
                //判断是否是第一次登陆
                isFristLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    //判断是否是第一登陆
    private void isFristLogin() {
        //创建sharedPreferences对象
        SharedPreferences sharePreference = getSharedPreferences("isFistLogin", Context.MODE_PRIVATE);
        //获得sharedPreferences对象中的isLogin属性
        boolean isLogin = sharePreference.getBoolean("isLogin",false);
        //如果是第一登陆,就跳转到引导界面，否则的话，跳转到主界面
        if(!isLogin){
            Intent guideIntent = new Intent(WelcomActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            finish();
        }else{
            Intent mainIntent = new Intent(WelcomActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

    }
}

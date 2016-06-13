package com.xieke.ciist.tomstudy.function;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.xieke.ciist.tomstudy.R;

/**
 * 截屏
 */
public class ShotSrceenActivity extends AppCompatActivity {
    private ImageView shotImg,frameAnimImg;
    private ImageButton img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_srceen);
        shotImg = (ImageView) findViewById(R.id.show_shot_img);
        frameAnimImg = (ImageView) findViewById(R.id.show_shot_img);
        ImageView rotateImg = (ImageView) findViewById(R.id.totate_img);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.btn_rotate_anim);
        LinearInterpolator lir = new LinearInterpolator();
        anim.setInterpolator(lir);
        rotateImg.startAnimation(anim);


        findViewById(R.id.shot_srceen_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShotSrceenDialog();
            }
        });


        img_btn = (ImageButton) findViewById(R.id.image_btn);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ShotSrceenActivity.this, R.anim.btn_checked_anim);

                img_btn.startAnimation(animation);
                Toast.makeText(ShotSrceenActivity.this,"加了动画的按钮",Toast.LENGTH_SHORT).show();
                picAnimtion();
            }
        });



    }


    /**
     * 单帧动画
     */
    private AnimationDrawable mAnimation;
    private void picAnimtion(){
    //   frameAnimImg.setBackgroundResource(R.anim.frame_pic);
        mAnimation = (AnimationDrawable) frameAnimImg.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一  AnimationDrawable单帧动画关键类
        frameAnimImg.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });
    }


    /**
     * 游戏切图dialog分享
     */
    private void popShotSrceenDialog(){
        final AlertDialog cutDialog = new AlertDialog.Builder(this).create();
        View dialogView = View.inflate(this, R.layout.show_cut_screen_layout, null);
        ImageView showImg = (ImageView) dialogView.findViewById(R.id.show_cut_screen_img);
        dialogView.findViewById(R.id.share_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cutDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.share_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShotSrceenActivity.this,"点击了share按钮",Toast.LENGTH_SHORT).show();
            }
        });
        //获取当前屏幕的大小
        int width = getWindow().getDecorView().getRootView().getWidth();
        int height = getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
        //找到当前页面的跟布局
        View view = getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        showImg.setImageBitmap(temBitmap);

        cutDialog.setView(dialogView);
        Window window = cutDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.8); // 高度设置为屏幕的0.6
        p.gravity = Gravity.CENTER;//设置弹出框位置
        window.setAttributes(p);
        window.setWindowAnimations(R.style.dialogWindowAnim);
        cutDialog.show();
    }
}

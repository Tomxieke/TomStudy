package com.xieke.ciist.tomstudy.wiget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.xieke.ciist.tomstudy.R;

public class WigetActivity extends AppCompatActivity {
    CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiget);

        String icondata = "http://wx.qlogo.cn/mmopen/iaQJtdAfN230APne5oqPru5q6SztpicAMZJgWZibaPnoahODjTECxYMDoKlna6WzrwFLe4TicCTJezcBWiabDuxZZVnRib7dwJH1XO/0";
        img = (CircleImageView) findViewById(R.id.cricleImageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setBorderColor(getResources().getColor(R.color.border_text));
                Toast.makeText(WigetActivity.this, "圆形头标被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        //    Picasso.with(this).load(icondata).into(img);
        Glide.with(this).load(icondata).into(img);


        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(icondata).into(imageView);


    }
}

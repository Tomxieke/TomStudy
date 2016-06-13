package com.xieke.ciist.tomstudy.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xieke.ciist.tomstudy.R;

import tyrantgit.explosionfield.ExplosionField;

public class ExplosionFieldActivity extends AppCompatActivity {
    private ImageView imageView;
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion_field);
        imageView = (ImageView) findViewById(R.id.explosion_field);
    //    explosionField.explode(null);
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(imageView);
    }



    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v);
                    Toast.makeText(ExplosionFieldActivity.this,"爆炸了",Toast.LENGTH_SHORT).show();
                //    v.setOnClickListener(null);
                }
            });
        }
    }
}

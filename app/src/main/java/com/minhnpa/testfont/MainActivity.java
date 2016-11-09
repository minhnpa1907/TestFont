package com.minhnpa.testfont;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    public static Typeface mFont;
    TextView txtView1;
    ImageButton imgButton;
    Button btnStart;

    //Hiệu ứng
    Animation animFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFont = Typeface.createFromAsset(getAssets(), "product-sans.ttf");
        txtView1 = (TextView) findViewById(R.id.txtView1);
        imgButton = (ImageButton) findViewById(R.id.imgButton);

        txtView1.setTypeface(mFont);

        String s = getPref("location");
        if ("on".equals(s)) {
            imgButton.setImageResource(R.drawable.switcher_on);
        }

        imgButton.setOnClickListener(this);

        btnStart = (Button) findViewById(R.id.btnStart);

        //load the animation
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        btnStart.setOnClickListener(this);
        animFadeIn.setAnimationListener(this);
    }

    public void savePref(String key, String value) {
        SharedPreferences preferences = getSharedPreferences("temp", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPref(String key) {
        SharedPreferences preferences = getSharedPreferences("temp", 0);
        return preferences.getString(key, null);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.imgButton) {
            String s = getPref("location");
            if ("off".equals(s)) {
                imgButton.setImageResource(R.drawable.switcher_on);
                savePref("location", "on");
            } else {
                imgButton.setImageResource(R.drawable.switcher_off);
                savePref("location", "off");
            }
        } else if (id == R.id.btnStart) {
            txtView1.startAnimation(animFadeIn);
        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        txtView1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

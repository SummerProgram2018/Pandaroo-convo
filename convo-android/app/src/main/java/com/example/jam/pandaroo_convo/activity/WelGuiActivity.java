package com.example.jam.pandaroo_convo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jam.pandaroo_convo.R;

import java.util.ArrayList;
import java.util.List;

public class WelGuiActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager welcome_pager;
    LinearLayout welcome_ll;
    Button welcome_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wel_gui);
        welcome_btn = findViewById(R.id.welcome_btn);
        initPoints();
        initView();
    }

    //init circle,add to view
    private void initPoints() {
        welcome_ll = findViewById(R.id.welcome_ll);
        for(int i = 0; i < images.length; i++){
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.setMargins(10,10,10,10);
            view.setBackgroundResource(R.drawable.welcome_select);
            view.setLayoutParams(params);
            welcome_ll.addView(view);
        }
        Log.e("TAG",":"+welcome_ll.getChildCount());
        welcome_ll.getChildAt(0).setSelected(true);
    }

    private void initView() {
        welcome_pager = findViewById(R.id.welcome_pager);
        MyAdapter myAdapter = new MyAdapter();
        welcome_pager.setAdapter(myAdapter);
        welcome_pager.addOnPageChangeListener(this);
        welcome_pager.setCurrentItem(0);


    }
    int[] images = {R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4};
    List<View> listOfView = new ArrayList<>();

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    int index = 0;

    @Override
    public void onPageSelected(int position) {
        welcome_ll.getChildAt(index).setSelected(false);
        welcome_ll.getChildAt(position%4).setSelected(true);
        index = position%4;
        if(index == 3){
            welcome_ll.setVisibility(View.INVISIBLE);
            welcome_btn.setVisibility(View.VISIBLE);
            welcome_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WelGuiActivity.this,LoginActivity.class));
                    finish();
                }
            });
        }else {
            welcome_ll.setVisibility(View.VISIBLE);
            welcome_btn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == ViewPager.SCROLL_STATE_IDLE){
            if(!handler.hasMessages(1)){
                startGuide();
            }
        }else {
            stopGuide();
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            welcome_pager.setCurrentItem(welcome_pager.getCurrentItem() + 1);
            sendEmptyMessageDelayed(1, 3000);//delay 2s send message to self
        }
    };
    private void startGuide() {
        handler.sendEmptyMessageDelayed(1, 3000);
    }
    private void stopGuide() {
        //
        handler.removeMessages(1);
    }

    class MyAdapter extends PagerAdapter {
        //slide times
        @Override
        public int getCount() {
            //one time listOfView.size();
            return listOfView.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
        public MyAdapter(){
            for(int i = 0;i < images.length; i++){
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(images[i]);
                listOfView.add(imageView);
            }
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //add item display
            //Toast.makeText(getBaseContext(), position + "page", Toast.LENGTH_SHORT).show();
            container.addView(listOfView.get(position % 4));
            return listOfView.get(position % 4);
        }

        //destory View object
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listOfView.get(position % 4));
        }
    }
}

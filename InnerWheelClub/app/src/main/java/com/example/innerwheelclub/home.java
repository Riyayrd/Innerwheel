package com.example.innerwheelclub;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class home extends AppCompatActivity {

    ViewPager viewPager;
    SlideAdapter slideAdapter;
    LinearLayout sliderdots;
    int dotscount;
    private ImageView[] dots;
    Timer timer;
    int currpos = 0;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth.getInstance().signOut();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notifications", "Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseApp.initializeApp(this);

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (!task.isSuccessful()) {

                        }
                    }
                });

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        sliderdots = findViewById(R.id.slidershots);
        slideAdapter = new SlideAdapter(this);

        viewPager.setAdapter(slideAdapter);

        dotscount = slideAdapter.getCount();
        dots = new ImageView[dotscount];


        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonacive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
            params.setMargins(0, 0, 0, 0);

            sliderdots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.actve_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonacive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.actve_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        createSlideshow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.getInstance().signOut();
    }

    public void createSlideshow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currpos == slideAdapter.getCount())
                    currpos = 0;

                viewPager.setCurrentItem(currpos++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 2500);
    }

    public void login_page(View view) {
        Context context = home.this;
        Class destActivity = login.class;

        Intent intent = new Intent(context, destActivity);
        startActivity(intent);
    }

    public void new_page(View view) {
        Intent intent = new Intent(home.this, new_user.class);
        startActivity(intent);
    }

    public void events(View view)
    {
        Intent intent = new Intent(home.this, recyclerview.class);
        startActivity(intent);
    }
    public void contct(View v)
    {
        Context context = home.this;
        Class destActivity = contact_us.class;

        Intent intent = new Intent(context, destActivity);
        startActivity(intent);
        //finish();
    }
}
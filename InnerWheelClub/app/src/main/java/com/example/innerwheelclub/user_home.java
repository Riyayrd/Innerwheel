package com.example.innerwheelclub;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class user_home extends AppCompatActivity {
String email,name;
    private FirebaseAuth mAuth;
    TextView tv,tv_phone;
    Button logout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        logout_btn=findViewById(R.id.logout_btn);
        //id=getIntent().getStringExtra("r_id");
        name=getIntent().getStringExtra("name");
        tv=(TextView)findViewById(R.id.details);
        tv_phone=findViewById(R.id.Phone_r);
       // textView.setText("Name: "+name+"\nRegistration id: "+email);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // No user is signed in
            Toast.makeText(this,"No user ",Toast.LENGTH_SHORT).show();
        } else {
            // User logged in
            email= currentUser.getEmail();
            name=currentUser.getUid();
            Log.e("USER:",email);
            Log.e("USER:",name);
            tv.setText("Registered Email: \n"+email);

        }

    }


    public void members(View view)
{
    Context context = user_home.this;
    Class destActivity = recycler_member.class;

    Intent intent = new Intent(context, destActivity);
    startActivity(intent);
}

    public void profile(View view)
    {
        Context context = user_home.this;
        Class destActivity = view_profile.class;

        Intent intent = new Intent(context, destActivity);
      intent.putExtra("email",email);
        startActivity(intent);

    }

    public void event(View view)
    {
        Context context = user_home.this;
        Class destActivity = recyclerview.class;

        Intent intent = new Intent(context, destActivity);
        intent.putExtra("email",email);
        startActivity(intent);

    }

    public void change(View view)
    {
        Context context = user_home.this;
        Class destActivity = change_password.class;

        Intent intent = new Intent(context, destActivity);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();

    }

    public void contact(View view)
    {
        Context context = user_home.this;
        Class destActivity = contact_us.class;

        Intent intent = new Intent(context, destActivity);
        startActivity(intent);
        finish();

    }
    public void volunteer(View v)
    {
        Intent intent=new Intent(user_home.this,Volunteering.class);
        startActivity(intent);
        //finish();
    }
    public void logout_intent(View v)
    {
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                AppState.getSingleInstance().setLoggingOut(true);
              //  Log.e(error , "Now log out and start the activity login");
                Intent intent = new Intent(user_home.this,home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(user_home.this,home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        startActivity(intent);
        finish();
    }
}

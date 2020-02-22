package com.example.innerwheelclub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cards extends AppCompatActivity {

    public String id;
    public TextView Title,Venue,Date,Time;
    public ImageView img;
    String ctitle;
    TextView title;
    Button vol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        id=getIntent().getStringExtra("title");

        img = findViewById(R.id.imageView3);
        Title = findViewById((R.id.Title));
        Venue =findViewById(R.id.Venue);
        Date = findViewById(R.id.Date);
        Time = findViewById(R.id.time);
        vol=findViewById(R.id.volunteer);
        switch(id)
        {
            case "Clothes Donation":
                img.setImageResource(R.drawable.clothes);
                Title.setText("Clothes Donation");
                Venue.setText("PICT");
                Date.setText("1/1/2019");
                Time.setText("4:00 pm");
                break;

            case "Garbage Collection":
                img.setImageResource(R.drawable.garbage);
                Title.setText("Garbage Collection");
                Venue.setText("PICT");
                Date.setText("1/1/2019");
                Time.setText("4:00 pm");
                break;

            case "Tree Plantation":
                img.setImageResource(R.drawable.tree);
                Title.setText("Tree Plantation");
                Venue.setText("PICT");
                Date.setText("1/1/2019");
                Time.setText("4:00 pm");
                break;

            case "Food Collection":
                img.setImageResource(R.drawable.food);
                Title.setText("Food Collection");
                Venue.setText("PICT");
                Date.setText("1/1/2019");
                Time.setText("4:00 pm");
                break;

        }


    }
    public void tovolunteer(View v)
    {
        title= v.findViewById(R.id.card_title);
        //ctitle= title.getText().toString();
        Log.e("passed id",id);
        Intent intent=new Intent(cards.this,volunteers.class);
        intent.putExtra("title",id);
        intent.putExtra("email","email");
        startActivity(intent);
    }




}

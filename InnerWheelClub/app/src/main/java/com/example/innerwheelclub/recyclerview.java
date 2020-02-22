package com.example.innerwheelclub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerview extends AppCompatActivity {
    TextView title;
    String ctitle;
    Button details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List nlist = new ArrayList<>();
        nlist.add(new item(R.drawable.clothes, "Clothes Donation"));
        nlist.add(new item(R.drawable.garbage, "Garbage Collection"));
        nlist.add(new item(R.drawable.tree, "Tree Plantation"));
        nlist.add(new item(R.drawable.food, "Food Collection"));
        Adapter adapter = new Adapter(this, nlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
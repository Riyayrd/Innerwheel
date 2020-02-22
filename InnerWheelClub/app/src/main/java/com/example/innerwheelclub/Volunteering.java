package com.example.innerwheelclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Volunteering extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    TextView tv_list;
    String mail;
    int flag;

    private ArrayList<Type> mArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteering);
        flag=0;
        mArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mail = currentUser.getEmail();
        tv_list=findViewById(R.id.tv_list);

        db.collection("users").document(mail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();

                if (document.exists()) {
                    ArrayList<String> list = (ArrayList<String>) document.get("volunterring");
                   // Log.e("msg",list.toString());
                   // tv_list.setText(list.toString());
                    StringBuilder builder = new StringBuilder();
                      for (String details : list) {
                            builder.append(details + "\n");
                        }
                        tv_list.setText(builder.toString());

                }
                }

        });
}}


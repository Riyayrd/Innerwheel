package com.example.innerwheelclub;

import android.os.Bundle;

import android.text.TextUtils;

import android.util.Log;

import android.widget.TextView;

import android.widget.Toast;



import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;



import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.annotations.NotNull;

import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.DocumentSnapshot;

import com.google.firebase.firestore.FieldValue;

import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.SetOptions;



import java.util.HashMap;

import java.util.Map;



public class volunteers extends AppCompatActivity {

    TextView tv_name,Congratulations;

    FirebaseFirestore db;

    String mail,id,name;

    CollectionReference collection, cf;

    String NAME_KEY;

    private FirebaseAuth mAuth;
    // TextView textDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);
        id = getIntent().getStringExtra("title");
        db = FirebaseFirestore.getInstance();
        tv_name = findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Congratulations = findViewById(R.id.Congratulations);
        if (TextUtils.equals(String.valueOf(currentUser),"null")) {
            Toast.makeText(this, "No user ", Toast.LENGTH_SHORT).show();
            Congratulations.setText("OOPS!!!You cannot volunteer as you are not a member yet!!!");
        } else {

            // User logged in
            mail = currentUser.getEmail();
            name = currentUser.getUid();
            Log.e("USER:", mail);
            Log.e("Name: ", name);
            Congratulations.setText(mail + " congratulations you are a volunteer now");
            Map<String, Object> newVolunteer = new HashMap<>();
            Map<String, Object> newVolunteers = new HashMap<>();
            newVolunteer.put(name, mail);
            newVolunteers.put("newVolunteers", newVolunteer);
            Log.e("id", id);
            Log.e("mail", mail);
            db.collection("Volunteer").document(id).set(newVolunteers, SetOptions.merge())
                    //    db.collection("Volunteer").document(id).set(newVolunteer)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(volunteers.this, "Volunteer Registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {

                        @Override

                        public void onFailure(@NotNull Exception e) {

                            Toast.makeText(volunteers.this, "ERROR" + e.toString(),

                                    Toast.LENGTH_SHORT).show();

                            Log.d("TAG", e.toString());

                        }

                    });

            db.collection("users").document(mail).update("volunterring", FieldValue.arrayUnion(id))

                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override

                        public void onSuccess(Void aVoid) {

                            Toast.makeText(volunteers.this, "event added to your data",
                                    Toast.LENGTH_SHORT).show();
                        }

                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NotNull Exception e) {
                            Toast.makeText(volunteers.this, "ERROR" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("TAG", e.toString());
                        }

                    });
        }
    }
}
package com.example.innerwheelclub;

/*
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

public class new_user extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


    }
    public void add(View view)
    {
        EditText name =findViewById(R.id.et_name);
        EditText r_id =findViewById(R.id.et_id);
        EditText email =findViewById(R.id.et_email);
        EditText phno =findViewById(R.id.et_phno);
        EditText res =findViewById(R.id.et_haddr);
        EditText office =findViewById(R.id.et_oaddr);
        EditText club =findViewById(R.id.et_club);
        EditText dob =findViewById(R.id.et_dob);
        EditText doj =findViewById(R.id.et_doj);
        EditText pass =findViewById(R.id.et_pw);

        users_data d1=new users_data(name.getText().toString(),
                r_id.getText().toString(),
                doj.getText().toString(),
                club.getText().toString(),
                email.getText().toString(),
                phno.getText().toString(),
                res.getText().toString(),
                office.getText().toString(),
                dob.getText().toString(),
                pass.getText().toString());


        mFirebaseInstance = FirebaseDatabase.getInstance("https://innerwheelclub-bc153.firebaseio.com/");

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Users");

       mFirebaseDatabase.child(d1.r_id).setValue(d1);
        mFirebaseDatabase.child(d1.r_id).setValue(d1);

        // app_title change listener
        mFirebaseInstance.getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Success","db created");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Error","no db created",error.toException());
            }
        });
        Toast.makeText(this,"Member added",Toast.LENGTH_SHORT).show();
        finish();

    }
}
*/

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class new_user extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    CollectionReference cr;
    EditText Name, mail, password, retypePassword, contact,r_id,res,office,club,dob,doj;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        cr = db.collection("users");
        Name = findViewById(R.id.et_name);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pw);
        r_id = findViewById(R.id.et_id);
        res = findViewById(R.id.et_haddr);
        office = findViewById(R.id.et_oaddr);
        club = findViewById(R.id.et_club);
        dob = findViewById(R.id.et_dob);
        doj = findViewById(R.id.et_doj);


        signUp = findViewById(R.id.create);
        contact = findViewById(R.id.et_phno);
// passwordCheck(password.getText().toString(),retypePassword.getText().toString());


    }
    public void create_user(View view) {
        Toast.makeText(new_user.this, " func entered", Toast.LENGTH_SHORT).show();
        if (!Name.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !contact.getText().toString().isEmpty()) {
            users_data d1 = new users_data(Name.getText().toString(),
                    r_id.getText().toString(),
                    doj.getText().toString(),
                    club.getText().toString(),
                    mail.getText().toString(),
                    contact.getText().toString(),
                    res.getText().toString(),
                    office.getText().toString(),
                    dob.getText().toString(),
                    password.getText().toString());
            cr.document(mail.getText().toString()).set(d1).addOnSuccessListener(new OnSuccessListener< Void >() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(new_user.this, "User Registered", Toast.LENGTH_SHORT).show();
                    firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(new_user.this, "User Registered", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(new_user.this, login.class));
                                    } else {
                                        Toast.makeText(new_user.this, "Some Error Occured Try Again 1", Toast.LENGTH_SHORT).show();
                                        task.getException().getMessage();

                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(new_user.this, "ERROR" + e.toString(),
                                            Toast.LENGTH_SHORT).show();

                                    Log.d("TAG", e.toString());
                                }
                            });
                    /*.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(new_user.this, " listener", Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()) {
                                firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(new_user.this, "User Registered", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(new_user.this, login.class));
                                                } else {
                                                    Toast.makeText(new_user.this, "Some Error Occured Try Again 1", Toast.LENGTH_SHORT).show();
                                                    task.getException().getMessage();

                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(new_user.this, "Some Error Occured Try Again 2", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("error", e.getMessage());
                }
            });

        } else {
            Toast.makeText(new_user.this, "else", Toast.LENGTH_SHORT).show();
        }*/
                }
            });}
    }

    public void passwordCheck(View v) {
        if (!password.getText().toString().equals(retypePassword.getText().toString()))
            retypePassword.setError("Enter same password");
    }
}
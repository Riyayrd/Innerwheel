package com.example.innerwheelclub;

/*public class login extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String a="test",b="test",c="test";
    static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void newuser(View v)
    {
        Context context =this;
        Class destActivity = new_user.class;

        Intent intent = new Intent(context, destActivity);
        startActivity(intent);
        finish();
    }
    public void loginned(View v) {

        i++;

        final EditText id = findViewById(R.id.ID_et);
        final EditText pass = findViewById(R.id.password_et);

        mFirebaseInstance = FirebaseDatabase.getInstance("https://innerwheelclub-bc153.firebaseio.com/");

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Users");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot curr : dataSnapshot.getChildren()) {

                    String pw = (String) curr.child("pass").getValue();
                    String r_id = (String) curr.child("r_id").getValue();
                    String name = (String) curr.child("Name").getValue();
                    Log.d("msg", r_id + "pass" + pw);
                    if (r_id.equalsIgnoreCase(id.getText().toString()) && pw.equals(pass.getText().toString())) {
                        a = r_id;
                        b = pw;
                        c=name;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        Log.d("flag", String.valueOf(a));


        if (i == 2) {
            if(a.equals("test"))
            {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                id.setText(" ");
                pass.setText("");
                i=0;
                finish();

            }
            if (a.equals(id.getText().toString())) {
                i=0;
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                Context context = this;
                Class destActivity = user_home.class;

                Intent intent = new Intent(context, destActivity);
                intent.putExtra("r_id",a);
                intent.putExtra("name",c);
                startActivity(intent);
                finish();
            }

        }
    }

}*/
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText userName, passsWord;
    Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.button);
        userName = findViewById(R.id.ID_et);
        passsWord = findViewById(R.id.password_et);
        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = userName.getText().toString().trim();
                String password = passsWord.getText().toString().trim();
                if (!funuser()) {
                    Toast.makeText(login.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    passsWord.setError("Mandatory");
                }
                if (!funupass()) {
                    Toast.makeText(login.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    userName.setError("Mandatory");
                } else {
                    firebaseAuth.signInWithEmailAndPassword(mail, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    startActivity(new Intent(login.this, user_home.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });
    }

  /*  @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }*/

    boolean funuser() {
        if (userName.getText().toString().isEmpty()) {
            //collegeName.setError("Mandatory");
            return false;
        } else
            return true;

    }

    boolean funupass() {
        if (passsWord.getText().toString().isEmpty()) {
            //collegeName.setError("Mandatory");
            return false;
        } else
            return true;

    }
    public void changep(View view)
    {
        Context context = login.this;
        Class destActivity = change_password.class;

        Intent intent = new Intent(context, destActivity);
        startActivity(intent);
    }

}

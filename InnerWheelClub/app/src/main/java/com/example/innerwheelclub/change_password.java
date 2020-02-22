package com.example.innerwheelclub;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class change_password extends AppCompatActivity {
EditText mail;
Button btnReset;
ProgressBar progressBar;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mail=findViewById(R.id.etmail);
        auth=FirebaseAuth.getInstance();

    }



    public void verified(View view)
    {
            String email = mail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }

            //progressBar.setVisibility(View.VISIBLE);

            auth.sendPasswordResetEmail(email)

                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(change_password.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(change_password.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });





    }
}

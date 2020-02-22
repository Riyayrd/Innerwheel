package com.example.innerwheelclub;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class view_profile extends AppCompatActivity {

    FirebaseFirestore db;
    TextView tv_profile,tv_name,tv_mail,tv_phno,tv_doj,tv_dob,tv_oaddr,tv_raddr,tv_club,tv_id;
    List<Type> mArrayList;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        mail=getIntent().getStringExtra("email");
        db = FirebaseFirestore.getInstance();
        tv_profile=findViewById(R.id.profile);
        tv_name=findViewById(R.id.name);
        tv_club=findViewById(R.id.club);
        tv_doj=findViewById(R.id.doj);
        tv_dob=findViewById(R.id.dob);
        tv_oaddr=findViewById(R.id.oaddr);
        tv_raddr=findViewById(R.id.raddr);
        tv_mail=findViewById(R.id.email);
        tv_id=findViewById(R.id.id);
        tv_phno=findViewById(R.id.phno);




// Source can be CACHE, SERVER, or DEFAULT.

// Get the document, forcing the SDK to use the offline cache
        /*ocRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();
                    Log.e("msg", "Cached document data: " + document.getData());
                    users_data obj=document.toObject(users_data.class);
                    tv2.setText(obj.Name);

                } else {
                    Log.e("msg", "Cached get failed: ", task.getException());
                }
            }
        });*/
        //getListItems();
        ReadSingleContact();

    }

    private void ReadSingleContact() {
        DocumentReference user = db.collection("users").document(mail);
        user.get().addOnCompleteListener(new OnCompleteListener< DocumentSnapshot >() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    tv_name.setText("Name: "+doc.get("name"));
                    tv_mail.setText("Email: " +doc.get("email"));
                    tv_phno.setText("Mobile no: " +doc.get("phno"));
                    tv_club.setText("Club: " +doc.get("club_name"));
                    tv_id.setText("ID: " +doc.get("r_id"));
                    tv_oaddr.setText("Office addr: " +doc.get("off_addr"));
                    tv_raddr.setText("Res addr: " +doc.get("res_addr"));
                    tv_doj.setText("Date of Joining: " +doc.get("doj"));
                    tv_dob.setText("Date of birth:" +doc.get("dob"));

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}


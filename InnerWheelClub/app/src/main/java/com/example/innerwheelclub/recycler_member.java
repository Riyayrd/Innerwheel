package com.example.innerwheelclub;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class recycler_member extends AppCompatActivity {

    FirebaseFirestore db;
    TextView tv_email,tv_name,tv_phno;
    RecyclerView recycler_view_m;
    //List<item_member> nlist=new ArrayList<>();
    List nlist = new ArrayList<>();
    String mail,n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_member);

        mail=getIntent().getStringExtra("email");
        db = FirebaseFirestore.getInstance();
        tv_name=findViewById(R.id.Name_r);
       tv_phno=findViewById(R.id.Phone_r);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
         recycler_view_m = findViewById(R.id.rv_member);
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                n= (String) document.getData().get("name");
                                Log.d("List",document.getId() + " => " + n);
                                p= (String) document.getData().get("phno");
                                nlist.add(new item_member(n,p));
                            }
                            Log.e("LLL", String.valueOf(nlist));
                            Adapter_member adapter = new Adapter_member(recycler_member.this, nlist);
                            recycler_view_m.setAdapter(adapter);
                            recycler_view_m.setLayoutManager(new LinearLayoutManager(recycler_member.this));
                        } else {
                            Log.d("List", "Error getting documents: ", task.getException());
                        }
                    }
                });



    }
    public void call_member(View view)
    {
        /*Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(number));
        Intent chooser= Intent.createChooser(callIntent,"title");
        startActivity(callIntent);*/
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",p, null));
        startActivity(intent);
    }

}

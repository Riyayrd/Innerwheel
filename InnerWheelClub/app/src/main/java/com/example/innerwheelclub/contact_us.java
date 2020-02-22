package com.example.innerwheelclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class contact_us extends AppCompatActivity {
String number,email_id;
TextView no,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        no=findViewById(R.id.textView);
        email=findViewById(R.id.textView5);
        number=no.getText().toString();
        email_id=email.getText().toString();
    }

    public void call(View view)
    {
        /*Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(number));
        Intent chooser= Intent.createChooser(callIntent,"title");
        startActivity(callIntent);*/
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
        startActivity(intent);
    }
    public void mail(View view)
    {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Enquiry" + "&to=" +email_id);
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
}

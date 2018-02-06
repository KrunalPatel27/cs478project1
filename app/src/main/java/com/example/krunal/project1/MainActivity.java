package com.example.krunal.project1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button1;
    Button button2;
    String phone;
    String enteredPhone = "Not Initialized";
    Boolean status =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button_1);
        button2 = (Button)findViewById(R.id.button_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(intent, 1) ;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status){
                    Intent anIntent = new Intent() ;
                    anIntent.setAction(Intent.ACTION_DIAL) ;
                    System.out.println(phone);
                    anIntent.setData(Uri.parse(phone)) ;
                    startActivity(anIntent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Incorrect Phone number: " + enteredPhone , Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                status = true;
                enteredPhone = data.getStringExtra("phone");
                phone = "tel:" + enteredPhone;
            }
            else if (resultCode == RESULT_CANCELED) {
                status = false;
                enteredPhone = data.getStringExtra("phone");
                phone = null;
            }
        }
    }//onActivityResult
}

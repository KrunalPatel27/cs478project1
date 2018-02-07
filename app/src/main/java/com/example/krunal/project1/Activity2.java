package com.example.krunal.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity2 extends Activity {
    EditText editText1;
    Button button1;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        editText1 = (EditText) findViewById(R.id.phone);
        button1 = (Button) findViewById(R.id.button_done);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = editText1.getText().toString();
                eventReturnResult();
            }
        });
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    phone = editText1.getText().toString();
                    eventReturnResult();
                    return true;
                }
                return false;
            }
        });

    }

    private void eventReturnResult(){
        if(isPhoneNumberValid(phone)){
            System.out.println("pass:" + phone);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("phone",phone);
            setResult(RESULT_OK,returnIntent);
            finish();
        }else {
            System.out.println("fails");
            Intent returnIntent = new Intent();
            returnIntent.putExtra("phone",phone);
            setResult(RESULT_CANCELED,returnIntent);
            finish();
        }
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        boolean isValid = false;

        //Initialize reg ex for phone number.
        //String expression = "^\\(([0-9]{3})\\)[-.\\s]([0-9]{3})[-.\\s]([0-9]{4})$";
        String expression = "[(][0-9]{3}[)][ ]?[0-9]{3}[-][0-9]{4}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }
}

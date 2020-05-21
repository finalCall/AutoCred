package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import co.aenterhy.toggleswitch.ToggleSwitchButton;

public class paySpinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_spinner);

        ToggleSwitchButton toggle = (ToggleSwitchButton) findViewById(R.id.toggle);
        toggle.setOnTriggerListener(new ToggleSwitchButton.OnTriggerListener() {
            @Override
            public void toggledUp() {
                Toast.makeText(getApplicationContext(),"Pay", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),payActivity.class);
                startActivity(i);
            }

            @Override
            public void toggledDown() {
                Toast.makeText(getApplicationContext(),"Recieve", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),recieveActivity.class);
                startActivity(i);
            }
        });
    }
}

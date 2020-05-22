package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class payActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Button paymentButton = (Button) findViewById(R.id.paymentButton);
        Button paymentRcvButton = (Button) findViewById(R.id.paymentRcvButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),confirmPay.class);
                startActivity(i);
            }
        });

        paymentRcvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),recieveActivity.class);
                startActivity(i);
            }
        });
    }
}

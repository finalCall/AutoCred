package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class payActivity extends AppCompatActivity {

    EditText phoneEditText;
    EditText amountEditText;
    String URL_Pay = "http://192.168.100.4:5000/api/user/pay/";
    String URL_Wallet = "http://192.168.100.4:5000/api/user/wallet/";

    public void checkBalance() {

        String URL_GET = URL_Wallet + "abc";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(payActivity.this, "Current Balance." + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(payActivity.this, "Error!"+ error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void payRequest() {
        String URL_POST = URL_Pay + "/987/9089/15";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(payActivity.this, "Amount Transfered!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),confirmPay.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(payActivity.this, "Error! Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        EditText amountEditText = (EditText) findViewById(R.id.paymentAmount);
        EditText phoneEditText = (EditText) findViewById(R.id.userNumber);

        checkBalance();

        Button paymentButton = (Button) findViewById(R.id.paymentButton);
        Button paymentRcvButton = (Button) findViewById(R.id.paymentRcvButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBalance();
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

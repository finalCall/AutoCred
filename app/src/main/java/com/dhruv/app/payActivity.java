package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class payActivity extends AppCompatActivity {

    EditText phoneEditText;
    EditText amountEditText;
    TextView currentBalanceView;
    Bundle bundle;
    String userName;

    String URL_Pay = "https://autocred-server.herokuapp.com/api/user/pay/";
    String URL_Wallet = "https://autocred-server.herokuapp.com/api/user/wallet/";

    public void checkBalance() {

        String URL_GET = URL_Wallet + userName;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(payActivity.this, "Current Balance." + response.toString(), Toast.LENGTH_SHORT).show();
                currentBalanceView.setText("Current Balance : Rs." + response.toString());
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
        String URL_POST = URL_Pay + userName + "/" + phoneEditText.getText().toString() + "/" + amountEditText.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(payActivity.this, "Amount Transfered!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),confirmPay.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("userName", userName);
                i.putExtras(bundle2);
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

        bundle = getIntent().getExtras();
        userName = bundle.getString("userName");

        amountEditText = (EditText) findViewById(R.id.paymentAmount);
        phoneEditText = (EditText) findViewById(R.id.phone);
        currentBalanceView = (TextView) findViewById(R.id.currentBalance);

        checkBalance();

        Button paymentButton = (Button) findViewById(R.id.paymentButton);
        Button paymentRcvButton = (Button) findViewById(R.id.paymentRcvButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRequest();
            }
        });

        paymentRcvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),recieveActivity.class);

                Bundle bundle1 = new Bundle();
                bundle1.putString("userName", userName);
                i.putExtras(bundle1);

                startActivity(i);
            }
        });
    }
}

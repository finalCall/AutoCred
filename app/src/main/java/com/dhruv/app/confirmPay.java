package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class confirmPay extends AppCompatActivity {

    Bundle bundle;
    String userName;

    TextView currentBalanceView;
    String URL_Wallet = "https://autocred-server.herokuapp.com/api/user/wallet/";

    public void checkBalance() {

        String URL_GET = URL_Wallet + userName;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(confirmPay.this, "Current Balance." + response.toString(), Toast.LENGTH_SHORT).show();
                currentBalanceView.setText("Current Balance : Rs." + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(confirmPay.this, "Error!"+ error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pay);
        bundle = getIntent().getExtras();
        userName = bundle.getString("userName");

        Button homeButton = (Button) findViewById(R.id.homeButton);
        currentBalanceView = (TextView) findViewById(R.id.currentBalance);

        checkBalance();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), payActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("userName", userName);
                i.putExtras(bundle1);
                startActivity(i);
            }
        });
    }
}

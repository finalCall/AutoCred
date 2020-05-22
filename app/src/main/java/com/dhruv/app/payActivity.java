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
    String URL_POST = "http://192.168.100.4:4000/user";


    public void payRequest() {
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("phone", phoneEditText.getText().toString().trim());
                params.put("amount", amountEditText.getText().toString().trim());

                return super.getParams();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        EditText amountEditText = (EditText) findViewById(R.id.paymentAmount);
        EditText phoneEditText = (EditText) findViewById(R.id.userNumber);

        Button paymentButton = (Button) findViewById(R.id.paymentButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRequest();
            }
        });
    }
}

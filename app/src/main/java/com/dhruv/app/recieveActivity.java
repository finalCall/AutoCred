package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class recieveActivity extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 1;
    Bundle bundle;
    String userName;
    EditText username2EditText;
    EditText amountEditText;

    String URL_Recieve = "https://autocred-server.herokuapp.com/api/user/receive/";

    public void payRequest() {
        String URL_POST = URL_Recieve + username2EditText.getText().toString() + "/" + userName + "/" + amountEditText.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(recieveActivity.this, "Amount Transfered!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),confirmPay.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("userName", userName);
                i.putExtras(bundle2);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(recieveActivity.this, "Error! Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        bundle = getIntent().getExtras();
        userName = bundle.getString("userName");
        username2EditText = findViewById(R.id.username2);
        amountEditText = findViewById(R.id.userAmount);

        Button paymentButton = (Button) findViewById(R.id.paymentButton);
        Button paymentNavigate = (Button) findViewById(R.id.payNavigateButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
            }
        });

        paymentNavigate.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        Uri videoUri = data.getData();

        if (requestCode == REQUEST_VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        videoUri, Toast.LENGTH_LONG).show();
                // Redirecting to spinner View

                payRequest();
                Intent in = new Intent(getApplicationContext(),confirmPay.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("userName", userName);
                in.putExtras(bundle2);
                startActivity(in);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Failed to authenticate.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to authenticate.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}

package com.dhruv.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        Button signupbtn = (Button) findViewById(R.id.signbtn);
        userNameTextView = findViewById(R.id.userName);

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(getApplicationContext(),payActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("userName", userNameTextView.getText().toString());
                i.putExtras(bundle);

                startActivity(i);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),signupView.class);
                startActivity(i);
            }
        });
    }
}

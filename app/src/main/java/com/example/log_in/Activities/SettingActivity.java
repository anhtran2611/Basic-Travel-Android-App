package com.example.log_in.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {
ImageView backIMG;
LinearLayout logoutBTN;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backIMG = findViewById(R.id.backImage);
        logoutBTN= findViewById(R.id.logoutBTN);

        backIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       logoutBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(SettingActivity.this, StartActivity.class);
               startActivity(intent);
           }
       });

    }
}

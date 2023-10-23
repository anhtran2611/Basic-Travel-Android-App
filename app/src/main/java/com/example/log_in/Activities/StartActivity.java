package com.example.log_in.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.log_in.R;

public class StartActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);


        ConstraintLayout introBtn =findViewById(R.id.introbtn);

      //XỬ lí sk khi ấn vào nút bắt đầu
      introBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //trỏ từ start activity sang activity đăng nhập
              Intent i1=new Intent(StartActivity.this, LoginActivity.class);
              startActivity(i1);

          }
      });






    }
}

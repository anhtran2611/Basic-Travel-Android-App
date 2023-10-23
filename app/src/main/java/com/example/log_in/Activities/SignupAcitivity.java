package com.example.log_in.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.log_in.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupAcitivity extends AppCompatActivity {
    private EditText editEmail, editPass;
    private Button buttonSignup;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editEmail = findViewById(R.id.edittext1);
        editPass = findViewById(R.id.edittext2);
        buttonSignup = findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();
//Sự kiện click vào nút signup
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();

            }
        });




    }

    private void signup(){
        String email,password;
        email=editEmail.getText().toString();
        password=editPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui lòng nhập email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Vui lòng nhập password",Toast.LENGTH_SHORT).show();
            return;
        }

//Xét dữ liệu với firebase
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
//Nếu thành công hiện ra chữ đăng kí thành công
                   if(task.isSuccessful()){
                   Toast.makeText(getApplicationContext(),"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
    //Thành công rồi thì trỏ giao diện sang login activity

                    Intent intent2 =new Intent(SignupAcitivity.this, LoginActivity.class);
                    startActivity(intent2);
                  }

                   else {
                       Toast.makeText(getApplicationContext(),
                               "Tạo tài khoản thất bại! ",
                               Toast.LENGTH_SHORT).show();
                   }
            }
        });






    }
}

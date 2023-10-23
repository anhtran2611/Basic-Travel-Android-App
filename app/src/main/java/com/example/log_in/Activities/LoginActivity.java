package com.example.log_in.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail, editPass;
    private Button buttonSignup, buttonLogin;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mAuth = FirebaseAuth.getInstance();


        editEmail = findViewById(R.id.edittext1);
        editPass = findViewById(R.id.edittext2);
        buttonLogin = findViewById(R.id.btnLogin);
        buttonSignup = findViewById(R.id.btnSignup);
//Xử lí sk khi edittext thay đổi

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editEmail.setError("Bạn phải nhập username");
                } else {
                    editEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editPass.setError("Bạn phải nhập Password");
                } else {
                    editPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


//Xử lí sk khi ấn button

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();



            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();

                Toast.makeText(LoginActivity.this,
                        "Đăng kí tài khoản mới! ",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void login(){
        //Lấy dữ liệu từ 2 edittext
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
         mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(Task<AuthResult> task) {
                 //Nếu thành công hiện ra chữ đăng nhập thành công
                 if(task.isSuccessful()){
                     Toast.makeText(getApplicationContext(),
                             "Đăng nhập thành công! ",
                             Toast.LENGTH_SHORT).show();

                     //Thành công rồi thì trỏ giao diện sang main activity

                     Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(getApplicationContext(),
                             "Đăng nhập thất bại! ",
                             Toast.LENGTH_SHORT).show();
                 }



             }

         });

    }
    private void signup(){
        //khi ấn signup thì nó sẽ trỏ từ bên login qua bên giao diên signup
        Intent i =new Intent(LoginActivity.this, SignupAcitivity.class);
        startActivity(i);

    }



}
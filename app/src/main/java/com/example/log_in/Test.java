package com.example.log_in;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Test extends AppCompatActivity {
    EditText editTextData;
    TextView textViewGetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        editTextData=findViewById(R.id.textpushData);
        Button buttonPush=findViewById(R.id.btn);
        Button buttonGet = findViewById(R.id.btn2);
        Button buttonDelete = findViewById(R.id.btn3);

        textViewGetData=findViewById(R.id.textgetData);

        buttonPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPushData();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGetDatabase();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteData();
            }
        });

    }
    //Hàm đẩy dữ liệu lên database
    public void onClickPushData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();//database
        DatabaseReference myRef = database.getReference("number");//key
//Truyền dữ liệu từ edittext lên
        //Kiểu int
        myRef.setValue(Integer.parseInt(editTextData.getText().toString().trim()), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                //Thông báo khi push thành công
                Toast.makeText(Test.this,"Push data success",Toast.LENGTH_SHORT).show();

            }
        });


    }
    //Hàm lấy dữ liệu về từ database
    public void onClickGetDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();//database
        DatabaseReference myRef = database.getReference("winter");//key


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(Integer.class);
                textViewGetData.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    //Xóa data
    public void onClickDeleteData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();//database
        DatabaseReference myRef = database.getReference("spring");//VD mình muốn xóa spring

        myRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                Toast.makeText(Test.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

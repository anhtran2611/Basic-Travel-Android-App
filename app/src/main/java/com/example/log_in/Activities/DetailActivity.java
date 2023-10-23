package com.example.log_in.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.example.log_in.Domains.PopularDomain;
import com.example.log_in.R;

public class DetailActivity extends AppCompatActivity {
private TextView TextTitle,TextLocation,TextBed,TextGuide,TextWifi,TextDescription,TextScore;
private PopularDomain item;
private ImageView PicIMG,BackBTN;
private AppCompatButton booknow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        initView();
        setVariable();
     }
     private void setVariable() {
        //Lấy dữ liệu từ domain
           item=(PopularDomain) getIntent().getSerializableExtra("object");
           TextTitle.setText(item.getTitle());
           TextScore.setText(""+(int)item.getScore());
           TextLocation.setText(item.getLocation());
           TextBed.setText(item.getBed()+"Bed");
           TextDescription.setText(item.getDescription());

//Nếu guide=true
           if(item.isGuide()){
                TextGuide.setText("Guide");

           }
           else{
               TextGuide.setText("No-Guide");
           }
//Nếu wifi=true
           if(item.isWifi()){
               TextWifi.setText("Wifi");
           }
           else{
             TextWifi.setText("No-Wifi");
         }
           //Lấy hình ảnh từ ngoài truyền vào
           int drawableResId=getResources().getIdentifier(item.getPic(),"drawable",getPackageName());


         Glide.with(this).load(drawableResId).into(PicIMG);
         //Khi click nút mũi tên ra ngoài
         BackBTN.setOnClickListener(v -> finish());
         booknow.setOnClickListener(v -> finish());
     }
     private void initView(){
        TextTitle=findViewById(R.id.textTitle);
        TextLocation=findViewById(R.id.textLocation);
        TextBed=findViewById(R.id.textBed);
        TextGuide=findViewById(R.id.textGuide);
        TextWifi=findViewById(R.id.textWifi);
        TextDescription=findViewById(R.id.textDescription);
        TextScore=findViewById(R.id.textScore);
        PicIMG=findViewById(R.id.picImg);
        BackBTN=findViewById(R.id.backBtn);
        booknow=findViewById(R.id.button);
     }

}
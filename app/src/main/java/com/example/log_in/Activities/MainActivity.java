package com.example.log_in.Activities;

import static com.example.log_in.DBManager.TABLENAME;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.log_in.Adapters.CategoryAdapter;
import com.example.log_in.Adapters.PopularAdapter;
import com.example.log_in.DBManager;
import com.example.log_in.Domains.CategoryDomain;
import com.example.log_in.Domains.PopularDomain;
import com.example.log_in.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//RecyclerView.Adapter Quản lý dữ liệu và cập nhật dữ liệu cần hiện thị vào View (phần tử hiện thị trong RecyclerView)
RecyclerView.Adapter adapterCategory, adapterPopular;
 RecyclerView recyclerViewPopular,recyclerViewCategory;
FloatingActionButton fab;
 AutoCompleteTextView autoCompleteTextView ;
    DBManager dbManager;
    List<CategoryDomain> categoryList;
    ImageView home, user, favorite, setting,Avatar,setting2;
    SQLiteDatabase sqLiteDatabase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();


//Sự kiện nút tìm kiếm
        autoCompleteTextView = findViewById(R.id.autoCompletePersonName);
        //Lấy dữ liệu từ string
        String[] countries = getResources().getStringArray(R.array.countries);
        //Đổ dữ liệu từ string vào arrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        //ĐỔ dữ liệu từ adapter vào autoCompleteTextView
        autoCompleteTextView.setAdapter(arrayAdapter);

        //Nút float button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
//SK trong thanh AppBar
        home = findViewById(R.id.idhome);
        user = findViewById(R.id.iduser);
        favorite = findViewById(R.id.idfavorite);
        setting = findViewById(R.id.idsetting);


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        //Avatar
        Avatar = findViewById(R.id.imageView4);
        Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        //Setting2
        setting2=findViewById(R.id.imageView6);
        setting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    //Sự kiện đổ dữ liệu lên recycle view

    private void  initRecyclerView(){
        dbManager = new DBManager(this);
       sqLiteDatabase = dbManager.getReadableDatabase();
//POPULAR
        ArrayList<PopularDomain> items=new ArrayList<>();
        items.add(new PopularDomain("Mar caible,avendia lago","Miami Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",2,true,4.8,"pic1",true,1000));
        items.add(new PopularDomain("Passo Rolle,TN","Hawaii Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",1,false,5,"pic2",true,2000));
        items.add(new PopularDomain("Gasta,Miami","Miami Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",3,true,4.8,"pic3",false,3000));
        items.add(new PopularDomain("Shark Ocean","Maldives Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",3,true,4.8,"pic4",true,6000));
        items.add(new PopularDomain("Penguin Coast","Madagascar Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",3,true,4.8,"pic5",false,2500));
        items.add(new PopularDomain("Cat Ba Island","Cat Ba Beach","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",3,true,5.0,"pic6",true,1000));
        items.add(new PopularDomain("Hot Desert","Sahara Desert","This 2 bed/1 bath home boasts an enormous," +
                "open-living plan, accented by striking" +
                "architectural features and high-end finishes." +
                "Feel inspired by open sight lines that"+
                "embrace the outdoors, crowned bt stunning"+
                "coffered ceilings",0,true,5.0,"pic7",false,1000));
        recyclerViewPopular=findViewById(R.id.view_popular);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//Đưa danh sách vào adapter
        adapterPopular=new PopularAdapter(items,MainActivity.this);
//Lấy danh sách từ adapter rồi hiển thị ra
        recyclerViewPopular.setAdapter(adapterPopular);


// CATEGORY
Cursor cursor=sqLiteDatabase.rawQuery("select *from "+TABLENAME+"",null);
        ArrayList<CategoryDomain> categoryList= new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[]image=cursor.getBlob(1);
            String title= cursor.getString(2);
            categoryList.add(new CategoryDomain(id,image,title));
        }
        cursor.close();

        recyclerViewCategory=findViewById(R.id.view_category);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));//recycleview hiển thị theo phong cách linear+chiều ngang


//Đưa danh sách vào adapter
        adapterCategory=new CategoryAdapter(categoryList,MainActivity.this,sqLiteDatabase);
//Lấy danh sách từ adapter rồi hiển thị ra
        recyclerViewCategory.setAdapter(adapterCategory);







    }



}



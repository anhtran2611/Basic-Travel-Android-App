package com.example.log_in.Activities;

import static com.example.log_in.DBManager.TABLENAME;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.log_in.DBManager;
import com.example.log_in.R;

import java.io.ByteArrayOutputStream;

public class UploadActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    ImageView uploadImage;
    Button uploadButton,finishButton,editButton;
    EditText uploadTitle;
    Uri selectedImage,imageUri;
    String imagePath;
    DBManager dbManager;
    int id;
    private static final int REQUEST_IMAGE_PICK = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_data);


        uploadImage = findViewById(R.id.uploadImage);
        uploadTitle = findViewById(R.id.uploadTitle);
        uploadButton = findViewById(R.id.uploadButton);
        editButton=findViewById(R.id.editButton);
        finishButton=findViewById(R.id.finishButton);
        dbManager = new DBManager(this);

        // Set an OnClickListener for the ImageView
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        //Click button
      insertData();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(UploadActivity.this,MainActivity.class);
               startActivity(intent);
                finish();
            }
        });

        editData();




    }




    // Xử lý lựa chọn hình ảnh
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    // Xử lý kết quả lựa chọn hình ảnh/Truy xuất URI của hình ảnh đã chọn và hiển thị nó trong ImageView.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                selectedImage = data.getData();
                imagePath = selectedImage.toString();
                // Hiển thị hình ảnh đã chọn trong ImageView thông qua thư viện Glide
                Glide.with(this).load(selectedImage).into(uploadImage);
            }

        }

    }









    public void insertData() {
        //Thêm
uploadButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ContentValues cv =new ContentValues();
        cv.put("picPath",ImageViewToByte(uploadImage));
        cv.put("title",uploadTitle.getText().toString());
        sqLiteDatabase=dbManager.getWritableDatabase();
        Long recinsert=sqLiteDatabase.insert(TABLENAME,null,cv);
        if(recinsert!=null) {
            Toast.makeText(UploadActivity.this, "insert successfully", Toast.LENGTH_SHORT).show();
            //Clear khi upload button
            uploadImage.setImageResource(R.drawable.upload);
            uploadTitle.setText("");
        }

    }
});
//Sửa
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("picPath",ImageViewToByte(uploadImage));
                cv.put("title",uploadTitle.getText().toString());
                sqLiteDatabase =dbManager.getWritableDatabase();
                long recedit=sqLiteDatabase.update(TABLENAME,cv,"id="+id,null);
                if (recedit!=-1){
                    Toast.makeText(UploadActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    //Clear khi edit button
                    uploadImage.setImageResource(R.drawable.upload);
                    uploadTitle.setText("");
                    //edit hide and submid visible
                    editButton.setVisibility(View.GONE);
                    uploadButton.setVisibility(View.VISIBLE);
                }
            }
        });



        }

 //Sửa(đổ dữ liệu từ Main qua)

 private void editData() {
     if(getIntent().getBundleExtra("categorydata")!=null){
         Bundle bundle=getIntent().getBundleExtra("categorydata");
         id=bundle.getInt("id");
         //đổ dữ liệu lại image
         byte[]bytes=bundle.getByteArray("picPath");
         Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
          uploadImage.setImageBitmap(bitmap);
          //title
         uploadTitle.setText(bundle.getString("title"));
         //visible edit button and hide upload button
         uploadButton.setVisibility(View.GONE);
         editButton.setVisibility(View.VISIBLE);

     }
 }

    private byte[] ImageViewToByte(ImageView uploadImage) {
        Bitmap bitmap = ((BitmapDrawable)uploadImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return bytes;

    }

}
package com.example.log_in.Adapters;

import static com.example.log_in.DBManager.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.log_in.Activities.UploadActivity;
import com.example.log_in.DBManager;
import com.example.log_in.Domains.CategoryDomain;
import com.example.log_in.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    //Vai trò của Adapter sẽ chuyển đổi một object tại một vị trí trở thành 1 hàng của danh sách sẽ được gắn vào RecyclerView.
//Xét từ bên class CategoryDomain qua đây
    List<CategoryDomain> categoryList;
    Context context;
    SQLiteDatabase sqLiteDatabase;

//Constructor


    public CategoryAdapter(List<CategoryDomain> categoryList, Context context, SQLiteDatabase sqLiteDatabase) {
        this.categoryList = categoryList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    //1.onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu, lấy view trong layout đưa vào adapter
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);



    }

    @Override
    //2.onBindViewHolder : hiển thị dữa liệu lên
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final CategoryDomain category = categoryList.get(position);
        holder.TextTitle.setText(category.getTitle());
        //Image
        byte[]image=category.getPicPath();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.picImg.setImageBitmap(bitmap);

//        Bitmap imageBitmap = BitmapFactory.decodeByteArray(category.getPicPath(), 0, category.getPicPath().length);
//
//        Glide.with(context)
//                .load(imageBitmap) // Load the Bitmap
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(holder.picImg);

        //Flowmenu
            holder.flowmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu= new PopupMenu(context,holder.flowmenu);
                    popupMenu.inflate(R.menu.flow_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.edit_menu:
                                    Bundle bundle= new Bundle();
                                    bundle.putInt("id",category.getId());
                                    bundle.putByteArray("picPath",category.getPicPath());
                                    bundle.putString("title",category.getTitle());
                                    Intent intent=new Intent(context, UploadActivity.class);
                                    intent.putExtra("categorydata",bundle);
                                    context.startActivity(intent);


                                    break;

                                case R.id.delete_menu:
                                    DBManager dbManager= new DBManager(context);
                                    sqLiteDatabase=dbManager.getReadableDatabase();
                                    long recdelete=sqLiteDatabase.delete(TABLENAME,"id="+category.getId(),null);
                                    if(recdelete!=-1){
                                        Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                                        //Remove position after delete
                                        categoryList.remove(position);
                                        //Update lại
                                        notifyDataSetChanged();

                                    }


                                    break;
                                default:
                                    return false;
                            }
                            return false;
                        }
                    });

                    //Display menu
                    popupMenu.show();
                }
            });


    }

    @Override
    //3.getItemCount : cho biết số phần tử của dữ liệu
    public int getItemCount() {
        return categoryList.size();
    }





//Dùng để ánh xạ các phần tử trong layout

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TextTitle;
       ImageView picImg;
       ImageButton flowmenu;
        public ViewHolder( View itemView) {
            super(itemView);

            TextTitle=itemView.findViewById(R.id.textTitle);
            picImg=itemView.findViewById(R.id.catImg);
            flowmenu=itemView.findViewById(R.id.flowmenu);
        }
    }
}

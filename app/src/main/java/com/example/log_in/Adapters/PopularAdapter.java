package com.example.log_in.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.log_in.Activities.DetailActivity;
import com.example.log_in.Domains.PopularDomain;
import com.example.log_in.R;

import java.util.ArrayList;

public class PopularAdapter  extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    //Xét từ bên class PopularDomain qua đây
    ArrayList<PopularDomain> items;
    Context context;

//constructer


    public PopularAdapter(ArrayList<PopularDomain> items, Context context) {
        this.items = items;
        this.context = context;
    }




    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);//Tạo đối tượng inflate


        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(  PopularAdapter.ViewHolder holder, int position ) {
        holder.TextTitle.setText(items.get(position).getTitle());
        holder.TextLocation.setText(items.get(position).getLocation());
        holder.TextScore.setText(""+items.get(position).getScore());

//Lấy hình ảnh
        int drawableResId=holder.itemView.getResources().getIdentifier(items.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(),new GranularRoundedCorners(40,40,40,40))
                .into(holder.picture);

//Khi click vào ảnh
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext() , DetailActivity.class);
               intent.putExtra("object",items.get(position));
               holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TextTitle,TextLocation,TextScore;
        ImageView picture;
        public ViewHolder( View itemView) {
            super(itemView);



            TextTitle=itemView.findViewById(R.id.textTitle);
            TextLocation=itemView.findViewById(R.id.textLocation);
            TextScore=itemView.findViewById(R.id.textScore);
            picture=itemView.findViewById(R.id.MiamiImg);

        }
    }
}

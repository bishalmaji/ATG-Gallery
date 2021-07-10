package com.bishal.atggallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bishal.atggallery.R;
import com.bishal.atggallery.model.ImageModel;
import com.bishal.atggallery.viewmodel.ImageViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Myholder> {
   private Context context;
   private List<ImageModel> imglist;
   private OnImageClickListener listener;
    public ImageAdapter(Context context, List<ImageModel> imglist) {
        this.context = context;
        this.imglist = imglist;
    }
    public void setImageList(List<ImageModel> imageModels){
        this.imglist=imageModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=  LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
     holder.title.setText(imglist.get(position).getTitle());
     Glide.with(holder.image).load(imglist.get(position).getUrl_s()).placeholder(R.drawable.progress).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if (this.imglist!=null){
            return this.imglist.size();
        }
        return 0;
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.r_title);
            image=itemView.findViewById(R.id.r_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION&&listener!=null){
                        listener.onItemClick(imglist.get(position).getUrl_s(),imglist.get(position).getTitle(),position);
                    }
                }
            });
        }

    }
    public interface  OnImageClickListener{
        void onItemClick(String url,String title,int position);
    }
    public void setOnItemClickListener(OnImageClickListener listener){
        this.listener=listener;
    }
}

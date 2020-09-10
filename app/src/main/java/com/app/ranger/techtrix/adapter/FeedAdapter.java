package com.app.ranger.techtrix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ranger.techtrix.R;
import com.app.ranger.techtrix.model.Feed;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder>{

    private Context mContext;
    private List<Feed> mData;
    RequestOptions option;

    public FeedAdapter(Context mContext, List<Feed> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.logout).error(R.drawable.logout);
    }

    @NonNull
    @Override
    public FeedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.model_feed,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.MyViewHolder holder, int position) {

        holder.tvalbum.setText(mData.get(position).getAlbumID());
        holder.tvid.setText(mData.get(position).getId());
        holder.tvtitle.setText(mData.get(position).getTitle());

        Glide.with(mContext).load(mData.get(position).getThumbUrl()).apply(option).into(holder.ivthumb);
        Glide.with(mContext).load(mData.get(position).getImageUrl()).apply(option).into(holder.ivpreview);


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvalbum,tvid,tvtitle;
        ImageView ivthumb,ivpreview;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvalbum = itemView.findViewById(R.id.tv_albumId);
            tvid = itemView.findViewById(R.id.tv_id);
            tvtitle = itemView.findViewById(R.id.tv_title);
            ivthumb = itemView.findViewById(R.id.iv_thumb);
            ivpreview = itemView.findViewById(R.id.iv_preview);

        }
    }


    }


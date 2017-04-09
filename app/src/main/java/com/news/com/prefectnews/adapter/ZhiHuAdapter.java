package com.news.com.prefectnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.com.prefectnews.R;
import com.news.com.prefectnews.activity.ZhiHuDetailActivity;
import com.news.com.prefectnews.bean.BaseData;
import com.news.com.prefectnews.image.GlideImageUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/6.
 */

public class ZhiHuAdapter extends RecyclerView.Adapter<ZhiHuAdapter.ViewHolder> {
  private ArrayList<BaseData.StoriesEntity> list;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        View viewitem;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            viewitem=itemView;
            imageView= (ImageView) itemView.findViewById(R.id.image_zhihu);
            textView= (TextView) itemView.findViewById(R.id.txt_zhihu);

        }
    }
    public ZhiHuAdapter(Context context, ArrayList<BaseData.StoriesEntity> list){
        this.list=list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihu_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.viewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=viewHolder.getAdapterPosition();
                Intent intent=new Intent(context, ZhiHuDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE_NEWS",1);
                bundle.putString("title",list.get(pos).getTitle());
                bundle.putInt("id",list.get(pos).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());

        GlideImageUtil.ShowImages(context,list.get(position).getImages().get(0),holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

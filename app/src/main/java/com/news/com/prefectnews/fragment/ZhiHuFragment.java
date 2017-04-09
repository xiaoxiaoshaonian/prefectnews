package com.news.com.prefectnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.news.com.prefectnews.R;
import com.news.com.prefectnews.adapter.ZhiHuAdapter;
import com.news.com.prefectnews.bean.BaseData;
import com.news.com.prefectnews.okhttputil.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/6.
 */

public class ZhiHuFragment extends Fragment {
    RecyclerView recyclerView;
    int page;
    ZhiHuAdapter adapter;
    public ZhiHuFragment(){

    }
    public ZhiHuFragment(int page){
        this.page=page;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_zhihu,null);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        String address="http://news-at.zhihu.com/api/4/news/before/20170122";
        OkHttpUtil.sendOkhttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("kkkk","请求异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data=response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJson(data);
                    }
                });

            }
        });

    }

    /**
     * 解析数据
     */
    private void parseJson(String jsondat){
        Gson gson=new Gson();
        ArrayList<BaseData.StoriesEntity> alist=new ArrayList<BaseData.StoriesEntity>();
        BaseData  tntity=gson.fromJson(jsondat,BaseData.class);
        // BaseData.StoriesEntity itntity=gson.fromJson(jsondat,new TypeToken<List<BaseData.StoriesEntity>>(){}.getType());
        alist.addAll(tntity.getStories());
         adapter=new ZhiHuAdapter(getActivity(),alist);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始布局
     */
    private void initview(){
        recyclerView= (RecyclerView) getView().findViewById(R.id.recycleview_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

}

package com.news.com.prefectnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.news.com.prefectnews.R;
import com.news.com.prefectnews.adapter.DouBanAdapter;
import com.news.com.prefectnews.bean.DouBanBaseData;
import com.news.com.prefectnews.okhttputil.OkHttpUtil;
import com.news.com.prefectnews.util.API;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/6.
 */

public class DouBanFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_douban,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView= (RecyclerView) getView().findViewById(R.id.douban_recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        String path= API.DOUBAN_LIST_URI+"2017-03-19";
        OkHttpUtil.sendOkhttpRequest(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("gggg","请求豆瓣异常");
              //  SnackbarUtil.useSnackbar(getView(),"请求豆瓣异常");
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
    private void parseJson(String jsondat){
        Gson gson=new Gson();
        ArrayList<DouBanBaseData.PostsEntity> alist=new ArrayList<DouBanBaseData.PostsEntity>();
        DouBanBaseData tntity=gson.fromJson(jsondat,DouBanBaseData.class);
        // BaseData.StoriesEntity itntity=gson.fromJson(jsondat,new TypeToken<List<BaseData.StoriesEntity>>(){}.getType());
        alist.addAll(tntity.getPosts());
        DouBanAdapter adapter=new DouBanAdapter(getActivity(),alist);
        recyclerView.setAdapter(adapter);
    }
}

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
import com.news.com.prefectnews.adapter.GuoQiaoAdapter;
import com.news.com.prefectnews.bean.GuoBaseData;
import com.news.com.prefectnews.okhttputil.OkHttpUtil;
import com.news.com.prefectnews.util.API;
import com.news.com.prefectnews.util.SnackbarUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/6.
 */

public class FruitFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_fruit,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView= (RecyclerView) getView().findViewById(R.id.guo_recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        OkHttpUtil.sendOkhttpRequest(API.GUOLISTURI, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                SnackbarUtil.useSnackbar(getView(),"请求果壳异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            final String data=response.body().string();
              //  parseJson(data);
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
        ArrayList<GuoBaseData.ResultEntity> alist=new ArrayList<GuoBaseData.ResultEntity>();
        GuoBaseData tntity=gson.fromJson(jsondat,GuoBaseData.class);
        // BaseData.StoriesEntity itntity=gson.fromJson(jsondat,new TypeToken<List<BaseData.StoriesEntity>>(){}.getType());
        alist.addAll(tntity.getResult());
       GuoQiaoAdapter adapter=new GuoQiaoAdapter(getActivity(),alist);
       recyclerView.setAdapter(adapter);
    }
}

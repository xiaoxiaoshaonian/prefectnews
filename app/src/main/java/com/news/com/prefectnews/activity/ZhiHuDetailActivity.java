package com.news.com.prefectnews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.news.com.prefectnews.R;
import com.news.com.prefectnews.bean.DouBanDetailData;
import com.news.com.prefectnews.bean.ZhiHuDetailData;
import com.news.com.prefectnews.image.GlideImageUtil;
import com.news.com.prefectnews.okhttputil.OkHttpUtil;
import com.news.com.prefectnews.util.API;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 知乎日报详情界面
 * Created by Administrator on 2017/3/14.
 */

public class ZhiHuDetailActivity extends AppCompatActivity {
    int ID, TYPE_NEWS;
    String title, address;
    WebView webView;
    ImageView imageView;
    TextView textView;
    ArrayList<ZhiHuDetailData> list;
    ArrayList<DouBanDetailData> douban_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihudetail_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.zhihu_deatil_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getID();//获取知乎日报ID;
        collapsingToolbarLayout.setTitle(title);
        webView = (WebView) findViewById(R.id.webview);
        imageView = (ImageView) findViewById(R.id.zhihu_detail_iamge);

        OkHttpUtil.sendOkhttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("hhhh:", "请求错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                ZhiHuDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJson(data);
                    }
                });

            }
        });
    }

    /**
     * 获取id
     */
    private void getID() {
        list = new ArrayList<ZhiHuDetailData>();
        douban_list = new ArrayList<DouBanDetailData>();
        Bundle bundle = getIntent().getExtras();
        TYPE_NEWS = bundle.getInt("TYPE_NEWS");
        ID = bundle.getInt("id");
        switch (TYPE_NEWS) {
            case 2:
                address = API.GUOQIAO_CONTENT + ID;
                break;
            case 3:
                address = API.DOUBAN_CONTENT + ID;
                break;
            default:
                address = API.ZHIHU_CONTENT + ID;
                break;
        }
        title = bundle.getString("title");
    }

    /**
     * 解析数据
     *
     * @param data
     */
    private void parseJson(String data) {
        Gson gson = new Gson();
        switch (TYPE_NEWS) {
            case 2:
                webView.loadUrl(address);
                break;
            case 3:
                DouBanDetailData douBanDetailData = gson.fromJson(data, DouBanDetailData.class);
                douban_list.add(douBanDetailData);
                webView.loadDataWithBaseURL(null, douban_list.get(0).getContent(), "text/html", "utf-8", null);
                GlideImageUtil.ShowImages(this, douban_list.get(0).getPhotos().get(0).getMedium().getUrl(), imageView);
                break;
            default:
                ZhiHuDetailData zhiHuDetailActivity = gson.fromJson(data, ZhiHuDetailData.class);
                list.add(zhiHuDetailActivity);
                webView.loadDataWithBaseURL(null, list.get(0).getBody(), "text/html", "utf-8", null);
                GlideImageUtil.ShowImages(this, list.get(0).getImage(), imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_toolbar, menu);
        return true;
    }
}

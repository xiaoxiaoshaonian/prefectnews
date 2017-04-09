package com.news.com.prefectnews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.news.com.prefectnews.adapter.PagerAdapter;
import com.news.com.prefectnews.fragment.DouBanFragment;
import com.news.com.prefectnews.fragment.FruitFragment;
import com.news.com.prefectnews.fragment.ZhiHuFragment;
import com.news.com.prefectnews.util.SnackbarUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ZhiHuFragment zhiHuFragment = null;
    DouBanFragment douBanFragment = null;
    FruitFragment fruitFragment = null;
    ViewPager viewPager;
    String title[]={"知乎日常","果壳精选","豆瓣一刻"};
    TabLayout tabLayout;
ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentArrayList=new ArrayList<>();
        zhiHuFragment=new ZhiHuFragment();
        douBanFragment=new DouBanFragment();
        fruitFragment=new FruitFragment();
        fragmentArrayList.add(zhiHuFragment);
        fragmentArrayList.add(fruitFragment);
        fragmentArrayList.add(douBanFragment);

        initView();
      //  relacefragment(1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackbarUtil.useSnackbar(MainActivity.this.getWindow().getDecorView(),"友情提示");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }
        if (id == R.id.action_topic) {

        }
        if (id == R.id.action_about) {

        }
        return super.onOptionsItemSelected(item);
    }

//    private void relacefragment(int num) {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        if (zhiHuFragment != null) {
//            transaction.hide(zhiHuFragment);
//        }
//        if (douBanFragment != null) {
//            transaction.hide(douBanFragment);
//            douBanFragment = new DouBanFragment();
//        }
//        if (fruitFragment != null) {
//            transaction.hide(fruitFragment);
//            fruitFragment = new FruitFragment();
//        }
//        if (num == 1) {
//            if (zhiHuFragment == null) {
//                zhiHuFragment = new ZhiHuFragment();
//                transaction.add(R.id.franmlayout_content, zhiHuFragment);
//            } else {
//                transaction.show(zhiHuFragment);
//            }
//        }
//       else if (num==2){
//            if (fruitFragment==null){
//                fruitFragment=new FruitFragment();
//                transaction.add(R.id.franmlayout_content,fruitFragment);
//            }
//            else{
//                transaction.show(fruitFragment);
//            }
//        }
//        else if (num==3){
//            if (douBanFragment==null){
//                douBanFragment=new DouBanFragment();
//                transaction.add(R.id.franmlayout_content,douBanFragment);
//
//            }
//            else {
//                transaction.show(douBanFragment);
//            }
//        }
//        transaction.commit();
//    }
    private void initView(){
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),title,fragmentArrayList));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}

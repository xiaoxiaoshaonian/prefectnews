package com.news.com.prefectnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/6.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private String title[];
    Fragment fragment=null;
    private  ArrayList<Fragment> fragmentArrayList;
    public PagerAdapter(FragmentManager fm,String title[],ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.title=title;
        this.fragmentArrayList=fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
 return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return title[position];
    }
}

package com.example.cuahang_doan.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MainAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>arrayFragment=new ArrayList<>();
    private ArrayList<String>arrayTitle=new ArrayList<>();

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
    public void addFragment(Fragment fm, String title){
        arrayFragment.add(fm);
        arrayTitle.add(title);
    }
}

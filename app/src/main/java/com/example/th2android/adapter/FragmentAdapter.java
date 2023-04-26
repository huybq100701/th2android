package com.example.th2android.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.th2android.fragment.InforFragment;
import com.example.th2android.fragment.ListFragment;
import com.example.th2android.fragment.SearchFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNum;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ListFragment fm1 = new ListFragment();
                return fm1;
            case 1:
                InforFragment fm2 = new InforFragment();
                return fm2;
            case 2:
                SearchFragment fm3 = new SearchFragment();

                return fm3;
        }

        return null;
    }

    @Override
    public int getCount() {
        return pageNum;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "DANH SACH";
            case 1:
                return "THONG TIN";
            case 2:
                return "TIM KIEM";
        }
        return null;
    }
}

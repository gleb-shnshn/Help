package com.papalam.help;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabCollectionAdapter extends FragmentStateAdapter {
    public TabCollectionAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ModelFragment();
            case 1:
                return new ScheduleFragment();
        }
        return new Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

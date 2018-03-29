package ke.co.ipandasoft.mmaraumobileinfo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Kevin Gitonga on 2/13/2018.
 */

public class MainFragmentsAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragments;

    public MainFragmentsAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Notices";
            case 1:
                return "Events";
            case 2:
                return "News";
        }

        return null;
    }

}

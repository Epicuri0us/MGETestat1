package testat.hsr.gadgeothek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import testat.hsr.gadgeothek.layout.GadgetListFragment;
import testat.hsr.gadgeothek.layout.LoanListFragment;
import testat.hsr.gadgeothek.layout.ReservationListFragment;

/**
 * Created by LucaAdmin on 24.10.2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                GadgetListFragment tab1 = new GadgetListFragment();
                return tab1;
            case 1:
                LoanListFragment tab2 = new LoanListFragment();
                return tab2;
            case 2:
                ReservationListFragment tab3 = new ReservationListFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

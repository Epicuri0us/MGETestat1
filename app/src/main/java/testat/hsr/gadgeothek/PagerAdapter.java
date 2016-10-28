package testat.hsr.gadgeothek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import testat.hsr.gadgeothek.layout.GadgetListFragment;
import testat.hsr.gadgeothek.layout.LoanListFragment;
import testat.hsr.gadgeothek.layout.ReservationListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                GadgetListFragment tab1 = new GadgetListFragment();
                return tab1;
            case 1:
                ReservationListFragment tab2 = new ReservationListFragment();
                return tab2;
            case 2:
                LoanListFragment tab3 = new LoanListFragment();
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

package testat.hsr.gadgeothek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import testat.hsr.gadgeothek.layout.GadgetListFragment;
import testat.hsr.gadgeothek.layout.LoanListFragment;
import testat.hsr.gadgeothek.layout.ReservationListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int TabCount;

    public PagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);

        TabCount = tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new GadgetListFragment();
            case 1:
                return new ReservationListFragment();
            case 2:
                return new LoanListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}

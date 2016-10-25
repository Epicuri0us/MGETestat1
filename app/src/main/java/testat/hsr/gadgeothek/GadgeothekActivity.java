package testat.hsr.gadgeothek;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import testat.hsr.gadgeothek.communication.ItemSelectionListener;
import testat.hsr.gadgeothek.layout.GadgetListFragment;
import testat.hsr.gadgeothek.layout.LoanListFragment;
import testat.hsr.gadgeothek.layout.ReservationListFragment;

public class GadgeothekActivity extends AppCompatActivity implements ItemSelectionListener {

    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gadgeothek_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.reservationmenu:
                toolbar.setTitle("Reservations");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                handleFragment(new ReservationListFragment());
                break;
            case R.id.loansmenu:
                toolbar.setTitle("Loans");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                handleFragment(new LoanListFragment());
                break;
        }
        return true;
    }

    private void handleFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        String backStateName = fragment.getClass().getName();
        Fragment f = fm.findFragmentByTag(backStateName);

        if(f == null){
            ft.replace(R.id.fragment, fragment,backStateName);
            ft.addToBackStack(backStateName);
        }else{
            ft.remove(f);
            ft.commit();
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment, f,backStateName);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgeothek);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadgets");
        setSupportActionBar(toolbar);
        handleFragment(new GadgetListFragment());

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Gadgets"));
        tabLayout.addTab(tabLayout.newTab().setText("Loans"));
        tabLayout.addTab(tabLayout.newTab().setText("Reservations"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public void onItemSelected(int position) {
        // TODO: 21.10.2016 Transition to new Fragment with detailView and reservation
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        FragmentTransaction ft = fm.beginTransaction();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            for(Fragment f: fm.getFragments()){
                ft.remove(f);
            }
            handleFragment(new GadgetListFragment());
            toolbar.setTitle("Gadgets");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //super.onBackPressed();
        }

    }
}

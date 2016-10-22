package testat.hsr.gadgeothek;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
                toolbar.setTitle("Gadgets");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportFragmentManager().popBackStack();
                break;
            case R.id.reservationmenu:
                toolbar.setTitle("Reservations");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                handleFragment(new ReservationListFragment(),true);
                break;
            case R.id.loansmenu:
                toolbar.setTitle("Loans");
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                handleFragment(new LoanListFragment(), true);
                break;
        }
        return true;
    }

    private void handleFragment(Fragment fragment, boolean replace){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        String backStateName = fragment.getClass().getName();
        Fragment f = fm.findFragmentByTag(backStateName);

        if(f == null){
            if(replace){
                ft.replace(R.id.fragment, fragment,backStateName);
                ft.addToBackStack(backStateName);
            }else{
                ft.add(R.id.fragment,fragment,backStateName);
            }

        }else{
            ft.replace(R.id.fragment, f,backStateName);

        }
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgeothek);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadgets");
        setSupportActionBar(toolbar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        GadgetListFragment fragment = new GadgetListFragment();
        ft.add(R.id.fragment,fragment);
        ft.commit();
    }

    @Override
    public void onItemSelected(int position) {
        // TODO: 21.10.2016 Transition to new Fragment with detailView and reservation
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            toolbar.setTitle("Gadgets");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            super.onBackPressed();
        }

    }
}

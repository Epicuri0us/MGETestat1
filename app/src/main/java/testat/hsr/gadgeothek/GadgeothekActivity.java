package testat.hsr.gadgeothek;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import testat.hsr.gadgeothek.communication.GadgetSelectionListener;
import testat.hsr.gadgeothek.communication.LoanSelectionListener;
import testat.hsr.gadgeothek.communication.ReservationSelectionListener;
import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.domain.Reservation;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

public class GadgeothekActivity extends AppCompatActivity implements GadgetSelectionListener
        ,LoanSelectionListener ,ReservationSelectionListener {

    private Toolbar toolbar;
    private Menu menu;
    private int itemextended = -1;
    private ViewPager viewPager;
    private MenuItem item;
    private Gadget gadgetToReserve;
    private PagerAdapter adapter;
    private FloatingActionButton buttonAdd;
    private FloatingActionButton buttonDelete;
    private Reservation reservation;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gadgeothek_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutmenu:
                LibraryService.logout(new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        pressBack();
                    }

                    @Override
                    public void onError(String message) {
                        Log.d(TAG, "onError() called with: message = [" + message + "]");
                    }
                });

                break;
            case R.id.addmenu:
                buttonAdd.setVisibility(View.GONE);
                item.setVisible(false);
                reserveGadget();
                break;
        }
        return true;
    }

    private void pressBack(){
        super.onBackPressed();
    }

    /*
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
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgeothek);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadgets");
        setSupportActionBar(toolbar);
        buttonAdd = (FloatingActionButton) findViewById(R.id.floatingadd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAdd.setVisibility(View.GONE);
                item.setVisible(false);
                reserveGadget();
            }
        });

        buttonDelete = (FloatingActionButton) findViewById(R.id.floatingdelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDelete.setVisibility(View.GONE);
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String message) {
                        Log.d(TAG, "onError() called with: message = [" + message + "]");
                    }
                });
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Gadgets"));
        tabLayout.addTab(tabLayout.newTab().setText("Reservations"));
        tabLayout.addTab(tabLayout.newTab().setText("Loans"));

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                buttonAdd.setVisibility(View.GONE);
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onGadgetSelected(int position, Gadget gadget) {
        gadgetToReserve = gadget;
        item = menu.findItem(R.id.addmenu);
        if(itemextended == position){
            itemextended = -1;
            item.setVisible(false);
            buttonAdd.setVisibility(View.GONE);
        }
        else{
            itemextended = position;
            item.setVisible(true);
            buttonAdd.setVisibility(View.VISIBLE);
        }

        // TODO: 21.10.2016 Transition to new Fragment with detailView and reservation
    }

    private void reserveGadget(){
        LibraryService.reserveGadget(gadgetToReserve, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                //adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(1);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onLoanSelected(int position, Loan loan) {

    }

    @Override
    public void onReservationSelected(int position, Reservation reservation) {
        this.reservation = reservation;

        if(itemextended == position){
            itemextended = -1;
            buttonDelete.setVisibility(View.GONE);
        }
        else{
            itemextended = position;
            buttonDelete.setVisibility(View.VISIBLE);
        }
    }
}

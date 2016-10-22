package testat.hsr.gadgeothek;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import testat.hsr.gadgeothek.communication.ItemSelectionListener;
import testat.hsr.gadgeothek.layout.GadgetListFragment;

public class GadgeothekActivity extends AppCompatActivity implements ItemSelectionListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gadgeothek_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgeothek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
}

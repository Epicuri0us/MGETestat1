package testat.hsr.gadgeothek;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import testat.hsr.gadgeothek.layout.ServerLoginFragment;
import testat.hsr.gadgeothek.communication.LoginHelper;
import testat.hsr.gadgeothek.communication.RegisterHelper;
import testat.hsr.gadgeothek.layout.LoginFragment;
import testat.hsr.gadgeothek.layout.RegisterFragment;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        RegisterHelper, LoginHelper {

    //used for logging
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settingsmenu:
                handleFragment(new ServerLoginFragment());
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        LibraryService.setServerAddress("http://10.0.2.2:8080/public");
        handleFragment(new LoginFragment());
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.register:
                handleFragment(new RegisterFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void RegisterHelper(final String email, final String password, String name, String matrikelnummer) {
        LibraryService.register(email, password, name, matrikelnummer, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Toast.makeText(getApplicationContext(), "Registration ok", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getSupportFragmentManager();
                fm.popBackStack();
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
                Toast.makeText(getApplicationContext(), "Error in Registration", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void LoginHelper(String email, String password) {
        LibraryService.login(email, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Toast.makeText(getApplicationContext(), "Login ok", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), GadgeothekActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
                Toast.makeText(getApplicationContext(), "Error in Login", Toast.LENGTH_LONG).show();
            }
        });
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
            ft.replace(R.id.fragment, f,backStateName);
        }
        ft.commit();
    }
}

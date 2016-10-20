package testat.hsr.gadgeothek;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import testat.hsr.gadgeothek.communication.LoginHelper;
import testat.hsr.gadgeothek.communication.RegisterHelper;
import testat.hsr.gadgeothek.layout.LoginFragment;
import testat.hsr.gadgeothek.layout.RegisterFragment;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        RegisterHelper, LoginHelper {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LibraryService.setServerAddress("http://mge1.dev.ifs.hsr.ch/public");
        handleFragment(new LoginFragment(), false);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.register:
                handleFragment(new RegisterFragment(),true);
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
                LoginHelper(email,password);
                // TODO: 20.10.2016 test argumentübergabe mit backbutton (autofill in fragment)
                //Intent intent = new Intent(getBaseContext(), GadgeothekActivity.class);
                //startActivity(intent);
                // FragmentManager fm = getSupportFragmentManager();
                //fm.popBackStack();
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });
    }

    @Override
    public void LoginHelper(String email, String password) {
        // TODO: 20.10.2016 example login, to be removed
        String userExample = "m@hsr.ch";
        String passwordExample = "12345";
        LibraryService.login(userExample, passwordExample, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Toast.makeText(getApplicationContext(), "Login ok", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), GadgeothekActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });
    }

    private void handleFragment(Fragment fragment, boolean replace){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(replace){
            ft.replace(R.id.fragment,fragment).addToBackStack(null);
        }else{
            ft.add(R.id.fragment,fragment);
        }
        ft.commit();
    }
}

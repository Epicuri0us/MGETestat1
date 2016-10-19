package testat.hsr.gadgeothek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import testat.hsr.gadgeothek.communication.RegisterHelper;
import testat.hsr.gadgeothek.layout.LoginFragment;
import testat.hsr.gadgeothek.layout.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RegisterHelper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handleFragment(new LoginFragment(), false);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.register:
                handleFragment(new RegisterFragment(),true);
                break;
            case R.id.email_sign_in_button:
                break;
        }

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

    // TODO: 19.10.2016
    @Override
    public void RegisterHelper(String email, String password, String name, String matrikelnummer) {
        //has to be implemented
    }
}

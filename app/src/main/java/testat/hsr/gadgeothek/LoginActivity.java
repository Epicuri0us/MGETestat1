package testat.hsr.gadgeothek;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import testat.hsr.gadgeothek.layout.LoginFragment;
import testat.hsr.gadgeothek.layout.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        LoginFragment fragment = new LoginFragment();

        ft.add(R.id.fragment,fragment);
        ft.commit();
    }

    public void onClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RegisterFragment fragment = new RegisterFragment();

        ft.replace(R.id.fragment,fragment);
        ft.commit();
    }

}

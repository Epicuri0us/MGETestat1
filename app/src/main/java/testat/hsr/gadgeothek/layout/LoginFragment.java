package testat.hsr.gadgeothek.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.communication.LoginHelper;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    LoginHelper loginHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        root.findViewById(R.id.register)
                .setOnClickListener((View.OnClickListener)getActivity());
        Button b = (Button)root.findViewById(R.id.email_sign_in_button);
        final EditText editMail = (EditText)root.findViewById(R.id.emailLogin);
        final EditText editPassword = (EditText)root.findViewById(R.id.passwordLogin);
        root.findViewById(R.id.serverButton).
                setOnClickListener((View.OnClickListener)getActivity());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginHelper.LoginHelper(editMail.getText().toString(),editPassword.getText().toString());
            }
        });
        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof View.OnClickListener)) {
            throw new
                    AssertionError("Activity must implement View.OnClickListener!");
        }
        if (!(context instanceof LoginHelper)){
            throw new
                    AssertionError("Activity must implement LoginHelper");
        }
        try {
            loginHelper = (LoginHelper) context;
        }
        catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement LoginHelper");
        }
    }


}

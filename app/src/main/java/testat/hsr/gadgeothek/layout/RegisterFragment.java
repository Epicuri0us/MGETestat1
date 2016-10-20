package testat.hsr.gadgeothek.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.communication.RegisterHelper;


public class RegisterFragment extends Fragment {

    RegisterHelper registerHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        Button button = (Button)root.findViewById(R.id.register_button);
        final EditText editName = (EditText)root.findViewById(R.id.name);
        final EditText editPassword = (EditText)root.findViewById(R.id.password);
        final EditText editMail = (EditText)root.findViewById(R.id.email);
        final EditText editMatrikelnummer = (EditText)root.findViewById(R.id.matrikelnummer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerHelper.RegisterHelper(editMail.getText().toString(),editPassword.getText().toString(),
                        editName.getText().toString(),editMatrikelnummer.getText().toString());
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof RegisterHelper)) {
            throw new
                    AssertionError("Activity should Implement RegisterHelper");
        }
        try {
            registerHelper = (RegisterHelper) context;
        }
        catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement RegisterHelper");
        }
    }
}

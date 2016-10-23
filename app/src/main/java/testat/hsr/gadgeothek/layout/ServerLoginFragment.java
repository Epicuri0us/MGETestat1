package testat.hsr.gadgeothek.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.service.LibraryService;

public class ServerLoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_server_login, container, false);

        final EditText editServerAddress = (EditText)root.findViewById(R.id.serverAddress);
        editServerAddress.setText(LibraryService.getServerAddress());

        root.findViewById(R.id.saveServerSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverAddress = editServerAddress.getText().toString();
                LibraryService.setServerAddress(serverAddress);

                Toast.makeText(getActivity().getApplicationContext(), "Set server address to: " + serverAddress, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}

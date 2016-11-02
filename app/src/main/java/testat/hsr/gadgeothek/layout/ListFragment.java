package testat.hsr.gadgeothek.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import testat.hsr.gadgeothek.GadgeothekActivity;

public abstract class ListFragment extends Fragment {

    protected GadgeothekActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (GadgeothekActivity)getActivity();

        return null;
    }
}

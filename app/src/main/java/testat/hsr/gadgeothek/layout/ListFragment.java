package testat.hsr.gadgeothek.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import testat.hsr.gadgeothek.GadgeothekActivity;
import testat.hsr.gadgeothek.communication.ItemSelectionListener;

public abstract class ListFragment extends Fragment {

    protected ItemSelectionListener itemSelectionCallback;
    protected GadgeothekActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (GadgeothekActivity)getActivity();

        return null;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (!(activity instanceof ItemSelectionListener)) {
            throw new IllegalStateException("Activity must implement ItemSelectionListener");
        }

        itemSelectionCallback = (ItemSelectionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemSelectionCallback = null;
    }
}

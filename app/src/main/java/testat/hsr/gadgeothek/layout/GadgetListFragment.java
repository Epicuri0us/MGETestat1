package testat.hsr.gadgeothek.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.GadgetAdapter;
import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class GadgetListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GadgetAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gadget_list, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                Log.d(TAG, "onCompletion() called with: input = [" + input + "]");
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new GadgetAdapter(input);
                mAdapter.setList(input);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });




        return root;
    }
}
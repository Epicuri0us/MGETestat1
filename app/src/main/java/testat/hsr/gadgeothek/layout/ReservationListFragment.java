package testat.hsr.gadgeothek.layout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.ReservationAdapter;
import testat.hsr.gadgeothek.communication.ReservationSelectionListener;
import testat.hsr.gadgeothek.domain.Reservation;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class ReservationListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ReservationSelectionListener itemSelectionCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewReservation);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {

                adapter =  new ReservationAdapter(input,itemSelectionCallback);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });


        return root;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (!(activity instanceof ReservationSelectionListener)) {
            throw new IllegalStateException("Activity must implement GadgetSelectionListener");
        }

        itemSelectionCallback = (ReservationSelectionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemSelectionCallback = null;
    }

}

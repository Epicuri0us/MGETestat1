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
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.ListAdapter;
import testat.hsr.gadgeothek.LoanViewHolder;
import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.ReservationViewHolder;
import testat.hsr.gadgeothek.communication.ItemSelectionListener;
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.domain.Reservation;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class ReservationListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ItemSelectionListener itemSelectionCallback;

    private TextView noEntries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        noEntries = (TextView)root.findViewById(R.id.noReservationEntries);

        recyclerView = (RecyclerView) root.findViewById(R.id.reservationRecyclerView);
        recyclerView.setHasFixedSize(true);
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                listAdapter = new ListAdapter<>(input, itemSelectionCallback, ReservationViewHolder.class);
                recyclerView.setAdapter(listAdapter);

                noEntries.setVisibility(input.isEmpty() ? View.VISIBLE : View.GONE);
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

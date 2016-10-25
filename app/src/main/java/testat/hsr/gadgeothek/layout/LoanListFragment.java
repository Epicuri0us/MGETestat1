package testat.hsr.gadgeothek.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import testat.hsr.gadgeothek.GadgetAdapter;
import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.communication.ItemSelectionListener;
import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class LoanListFragment extends Fragment {

    private RecyclerView recyclerView;
    private GadgetAdapter gadgetAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ItemSelectionListener itemSelectionCallback;

    private TextView noEntries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        noEntries = (TextView)root.findViewById(R.id.noLoanEntries);

        recyclerView = (RecyclerView) root.findViewById(R.id.loanRecyclerView);
        recyclerView.setHasFixedSize(true);
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                gadgetAdapter = new GadgetAdapter(getGadgetList(input), itemSelectionCallback);
                recyclerView.setAdapter(gadgetAdapter);

                noEntries.setVisibility(input.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError() called with: message = [" + message + "]");
            }
        });

        return root;
    }

    // Workaround; stream API is not available
    private List<Gadget> getGadgetList(List<Loan> loans) {
        List<Gadget> gadgets = new ArrayList<>();
        for (Loan loan : loans) {
            gadgets.add(loan.getGadget());
        }

        return gadgets;
    }
}

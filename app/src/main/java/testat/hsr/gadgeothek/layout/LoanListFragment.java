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

import testat.hsr.gadgeothek.LoanAdapter;
import testat.hsr.gadgeothek.R;
import testat.hsr.gadgeothek.communication.LoanSelectionListener;
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class LoanListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LoanSelectionListener itemSelectionCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewLoan);
        recyclerView.setHasFixedSize(true);

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new LoanAdapter(input,itemSelectionCallback);
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

        if (!(activity instanceof LoanSelectionListener)) {
            throw new IllegalStateException("Activity must implement GadgetSelectionListener");
        }

        itemSelectionCallback = (LoanSelectionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemSelectionCallback = null;
    }

}

package testat.hsr.gadgeothek.layout;

import android.os.Bundle;
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
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class LoanListFragment extends ListFragment {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView noEntries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        noEntries = (TextView)root.findViewById(R.id.noLoanEntries);

        recyclerView = (RecyclerView) root.findViewById(R.id.loanRecyclerView);
        recyclerView.setHasFixedSize(true);
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                listAdapter = new ListAdapter<>(input, LoanViewHolder.class, R.layout.loan_rowlayout, activity.getPagerAdapter());
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
}

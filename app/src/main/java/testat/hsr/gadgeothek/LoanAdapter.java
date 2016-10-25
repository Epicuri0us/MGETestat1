package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.domain.Loan;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    public class LoanViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView title;


        public LoanViewHolder(View itemRoot) {
            super(itemRoot);
            this.parent = itemRoot;
            this.title = (TextView)itemRoot.findViewById(R.id.titleLoan);
        }
    }

    private List<Loan> loanList;

    public LoanAdapter(List<Loan> loanList){
        this.loanList = loanList;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.loan_layout,parent,false);
        LoanViewHolder viewHolder = new LoanViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        Loan l = loanList.get(position);
        holder.title.setText(l.getGadget().getName());
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

}

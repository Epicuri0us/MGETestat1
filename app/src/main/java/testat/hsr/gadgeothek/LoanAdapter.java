package testat.hsr.gadgeothek;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import testat.hsr.gadgeothek.communication.LoanSelectionListener;
import testat.hsr.gadgeothek.domain.Loan;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    public class LoanViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView title;
        public LinearLayout expandable;
        public TextView pickUpDate;
        public TextView returnDate;


        public LoanViewHolder(View itemRoot) {
            super(itemRoot);
            this.parent = itemRoot;
            this.title = (TextView)itemRoot.findViewById(R.id.titleLoan);
            this.expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
            this.pickUpDate = (TextView)itemRoot.findViewById(R.id.pickupDate);
            this.returnDate = (TextView)itemRoot.findViewById(R.id.returnDate);
        }
    }

    private List<Loan> loanList;
    private LoanSelectionListener selectionListener;
    private int expandedPosition = -1;

    public LoanAdapter(List<Loan> loanList,LoanSelectionListener loanSelectionListener){
        this.loanList = loanList;
        selectionListener = loanSelectionListener;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.loan_layout,parent,false);
        LoanViewHolder viewHolder = new LoanViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, final int position) {
        Loan l = loanList.get(position);
        holder.title.setText(l.getGadget().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(l.getPickupDate() != null){
            holder.pickUpDate.setText("Pickup Date: " + dateFormat.format(l.getPickupDate()));
        }
        if(l.getReturnDate() != null){
            holder.returnDate.setText("Return Date: " + dateFormat.format(l.getReturnDate()));
        }


        if (position == expandedPosition) {
            holder.expandable.setVisibility(View.VISIBLE);
        } else {
            holder.expandable.setVisibility(View.GONE);
        }

        if(l.isOverdue()){
            holder.title.setTextColor(Color.RED);
        }
        else {
            holder.title.setTextColor(Color.BLACK);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExpansion(position);
                selectionListener.onLoanSelected(position,loanList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

    private void handleExpansion(int position){
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        // Set the current position to "expanded"
        if(expandedPosition == position){
            expandedPosition = -1;
            notifyItemChanged(expandedPosition);
        }else{
            expandedPosition = position;
            notifyItemChanged(expandedPosition);
        }

    }

}

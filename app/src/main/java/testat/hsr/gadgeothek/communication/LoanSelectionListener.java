package testat.hsr.gadgeothek.communication;

import testat.hsr.gadgeothek.domain.Loan;

public interface LoanSelectionListener {
    public void onLoanSelected(int position, Loan loan);
}

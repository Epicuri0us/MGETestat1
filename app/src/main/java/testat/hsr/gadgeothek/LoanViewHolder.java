package testat.hsr.gadgeothek;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.domain.Loan;
import testat.hsr.gadgeothek.service.LibraryService;

public class LoanViewHolder extends ItemViewHolder<Loan> {

    private TextView view;
    private TextView nameInner;
    private LinearLayout expandable;
    private TextView manufacturer;
    private TextView condition;
    private TextView price;
    private TextView inventorynr;
    private TextView pickupDate;
    private TextView returnDate;
    private TextView overdue;

    public LoanViewHolder(View itemRoot, ListAdapter listAdapter) {
        super(itemRoot, listAdapter);
        this.setParent(itemRoot);

        view = (TextView) itemRoot.findViewById(R.id.textView);
        nameInner = (TextView) itemRoot.findViewById(R.id.gadgetNameInner);
        expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
        manufacturer = (TextView) itemRoot.findViewById(R.id.manufacturer);
        condition = (TextView) itemRoot.findViewById(R.id.condition);
        price = (TextView) itemRoot.findViewById(R.id.price);
        inventorynr = (TextView) itemRoot.findViewById(R.id.inventorynr);
        pickupDate = (TextView) itemRoot.findViewById(R.id.pickupDate);
        returnDate = (TextView) itemRoot.findViewById(R.id.returnDate);
        overdue = (TextView) itemRoot.findViewById(R.id.overdue);
    }

    @Override
    public void bind(Loan loan, boolean expanded) {
        Gadget g = loan.getGadget();
        view.setText(g.getName());
        nameInner.setText(g.getName());
        inventorynr.setText(g.getInventoryNumber());
        price.setText(Double.toString(g.getPrice()));
        condition.setText(g.getCondition().toString());
        manufacturer.setText(g.getManufacturer());

        Drawable overdueIcon = view.getContext().getResources().getDrawable(android.R.drawable.presence_away);
        overdueIcon.setBounds(0, 0, 60, 60);

        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.DATE_FIELD);
        pickupDate.setText(dateFormat.format(loan.getPickupDate()));

        Date date = loan.getReturnDate();
        if(date != null)
            returnDate.setText(dateFormat.format(date));

        if(loan.isOverdue()) {
            overdue.setVisibility(View.VISIBLE);
            view.setCompoundDrawables(null, null, overdueIcon, null);
        }

        if (expanded) {
            view.setVisibility(View.GONE);
            expandable.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
            expandable.setVisibility(View.GONE);
        }
    }
}
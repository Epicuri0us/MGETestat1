package testat.hsr.gadgeothek;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import testat.hsr.gadgeothek.domain.Gadget;
import testat.hsr.gadgeothek.domain.Reservation;
import testat.hsr.gadgeothek.service.Callback;
import testat.hsr.gadgeothek.service.LibraryService;

import static android.content.ContentValues.TAG;

public class ReservationViewHolder extends ItemViewHolder<Reservation> {

    private TextView view;
    private TextView nameInner;
    private LinearLayout expandable;
    private TextView manufacturer;
    private TextView condition;
    private TextView price;
    private TextView inventorynr;
    private TextView reservationDate;
    private TextView waitingPosition;
    private Button cancelReservationButton;

    public ReservationViewHolder(View itemRoot, ListAdapter listAdapter) {
        super(itemRoot, listAdapter);
        this.setParent(itemRoot);

        view = (TextView) itemRoot.findViewById(R.id.textView);
        nameInner = (TextView) itemRoot.findViewById(R.id.gadgetNameInner);
        expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
        manufacturer = (TextView) itemRoot.findViewById(R.id.manufacturer);
        condition = (TextView) itemRoot.findViewById(R.id.condition);
        price = (TextView) itemRoot.findViewById(R.id.price);
        inventorynr = (TextView) itemRoot.findViewById(R.id.inventorynr);
        reservationDate = (TextView) itemRoot.findViewById(R.id.reservationDate);
        waitingPosition = (TextView) itemRoot.findViewById(R.id.waitingPosition);
        cancelReservationButton = (Button) itemRoot.findViewById(R.id.cancelReservation);
    }

    @Override
    public void bind(final Reservation reservation, boolean expanded) {
        final Gadget g = reservation.getGadget();

        Drawable finishedIcon = view.getContext().getResources().getDrawable(android.R.drawable.presence_online);
        finishedIcon.setBounds(0, 0, 60, 60);

        if(reservation.isReady()) {
            view.setCompoundDrawables(null, null, finishedIcon, null);
            nameInner.setCompoundDrawables(null, null, finishedIcon, null);
        }

        view.setText(g.getName());
        nameInner.setText(g.getName());

        inventorynr.setText(g.getInventoryNumber());
        price.setText(Double.toString(g.getPrice()));
        condition.setText(g.getCondition().toString());
        manufacturer.setText(g.getManufacturer());

        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.DATE_FIELD);
        reservationDate.setText(dateFormat.format(reservation.getReservationDate()));
        waitingPosition.setText(Integer.toString(reservation.getWatingPosition()));

        cancelReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Toast.makeText(view.getContext(), "Reservation of " + g.getName() + " has been cancelled.", Toast.LENGTH_SHORT).show();

                        card.startAnimation(slideOutAnimation);
                    }

                    @Override
                    public void onError(String message) {
                        Log.d(TAG, "onError() called with: message = [" + message + "]");
                        Toast.makeText(view.getContext(), "Error when trying to cancel reservation of " + g.getName() + ".", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if (expanded) {
            view.setVisibility(View.GONE);
            expandable.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
            expandable.setVisibility(View.GONE);
        }
    }
}
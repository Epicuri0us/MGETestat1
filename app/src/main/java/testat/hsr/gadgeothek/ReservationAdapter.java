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

import testat.hsr.gadgeothek.communication.ReservationSelectionListener;
import testat.hsr.gadgeothek.domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>{

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView title;
        public TextView reservationDate;
        public TextView waitingPosition;
        public LinearLayout expandable;

        public ReservationViewHolder(View itemRoot) {
            super(itemRoot);
            this.parent = itemRoot;
            this.title = (TextView)itemRoot.findViewById(R.id.textView);
            this.reservationDate = (TextView)itemRoot.findViewById(R.id.resDate);
            this.waitingPosition = (TextView)itemRoot.findViewById(R.id.waitingPosition);
            this.expandable = (LinearLayout) itemRoot.findViewById(R.id.expandableText);
        }
    }

    private List<Reservation> reservationList;
    private ReservationSelectionListener selectionListener;
    private int expandedPosition = -1;

    public ReservationAdapter(List<Reservation> reservationList,
                              ReservationSelectionListener reservationSelectionListener){
        this.reservationList = reservationList;
        selectionListener = reservationSelectionListener;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.reservation_layout,parent,false);
        ReservationViewHolder viewHolder = new ReservationViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, final int position) {
        final Reservation reservation = reservationList.get(position);
        holder.title.setText(reservation.getGadget().getName());
        holder.waitingPosition.setText("Waiting position: " +
                Integer.toString(reservation.getWatingPosition()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.reservationDate.setText("Reservationdate: " +
                dateFormat.format(reservation.getReservationDate()));

        if (position == expandedPosition) {
            holder.expandable.setVisibility(View.VISIBLE);
        } else {
            holder.expandable.setVisibility(View.GONE);
        }

        if(reservation.isReady()){
            holder.title.setTextColor(Color.GREEN);
        }else{
            holder.title.setTextColor(Color.BLACK);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExpansion(position);
                selectionListener.onReservationSelected(position,reservationList.get(position));
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
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

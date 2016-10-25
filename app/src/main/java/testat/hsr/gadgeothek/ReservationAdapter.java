package testat.hsr.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import testat.hsr.gadgeothek.domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>{

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        public View parent;
        public TextView title;

        public ReservationViewHolder(View itemRoot) {
            super(itemRoot);
            this.parent = itemRoot;
            this.title = (TextView)itemRoot.findViewById(R.id.titleReservation);
        }
    }

    private List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList){
        this.reservationList = reservationList;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.reservation_layout,parent,false);
        ReservationViewHolder viewHolder = new ReservationViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation r = reservationList.get(position);
        holder.title.setText(r.getGadget().getName());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

}

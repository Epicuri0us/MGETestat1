package testat.hsr.gadgeothek.communication;

import testat.hsr.gadgeothek.domain.Reservation;

public interface ReservationSelectionListener {
    public void onReservationSelected(int position, Reservation reservation);
}

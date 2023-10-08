package catss.kino.dto;

import catss.kino.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
    int id;
    int movieId;
    Double price;
    LocalDate reservationDate;
    List<String> seats;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.movieId = reservation.getShowing().getId();
        this.price = reservation.getShowing().getPrice();
        this.reservationDate = reservation.getBookingDate();
        this.seats = reservation.getSeats();
    }
}

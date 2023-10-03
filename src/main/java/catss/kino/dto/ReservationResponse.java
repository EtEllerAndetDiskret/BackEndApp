package catss.kino.dto;

import catss.kino.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
    int id;
    String movie;
    Double price;
    LocalDate reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.movie = String.valueOf(reservation.getMovie().getId());
        this.price = reservation.getMovie().getPrice();
        this.reservationDate = reservation.getBookingDate();
    }
}

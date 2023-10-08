package catss.kino.dto;

import catss.kino.entity.Member;
import catss.kino.entity.Reservation;
import catss.kino.entity.Showing;
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
    String username;
    int showingId;
    List<String> seats;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.username = reservation.getMember().getUsername();
        this.showingId = reservation.getShowing().getId();
        this.seats = reservation.getSeats();
    }
}

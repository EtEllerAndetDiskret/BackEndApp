package catss.kino.dto;

import catss.kino.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    int showingId;
    String userName;
    List<String> seats;

    public ReservationRequest(Reservation res) {
        this.showingId = res.getShowing().getId();
        this.userName = res.getMember().getUsername();
        this.seats = res.getSeats();
    }
}

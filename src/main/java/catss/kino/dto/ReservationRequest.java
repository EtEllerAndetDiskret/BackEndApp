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
    int movieId;
    String userName;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate date;
    List<String> seats;

    public ReservationRequest(Reservation res) {
        this.movieId = res.getMovie().getId();
        this.userName = res.getMember().getUsername();
        this.date = res.getBookingDate();
        this.seats = res.getSeats();
    }
}

package catss.kino.dto;

import catss.kino.entity.Showing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowingResponse {
    int movieId;
    int hallId;
    LocalDateTime startTime;
    Long lengthInMinutes;
    double price;
    List<Integer> reservationIds;

    public ShowingResponse(Showing showing) {
        this.movieId = showing.getMovie().getId();
        this.hallId = showing.getHallId();
        this.startTime = showing.getStart();
        this.lengthInMinutes = Duration.between(startTime, showing.getEnd()).toMinutes();
        this.price = showing.getPrice();
        this.reservationIds = showing.getReservationIds();
    }
}

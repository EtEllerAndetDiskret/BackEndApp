package catss.kino.dto;

import catss.kino.entity.Showing;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowingRequest {
    int movieId;
    int hallId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    LocalDateTime start;
    Long durationInMinutes;
    double price;

    public ShowingRequest(Showing s) {
        this.movieId = s.getMovie().getId();
        this.hallId = s.getHallId();
        this.start = s.getStart();
        this.durationInMinutes = Duration.between(start, s.getEnd()).toMinutes();
        this.price = s.getPrice();
    }
}

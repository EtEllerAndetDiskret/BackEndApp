package catss.kino.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reservation {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDate bookingDate;

    @ManyToOne
    Member member;

    @ManyToOne
    Movie movie;

    @CreationTimestamp
    protected LocalDateTime created;
    @UpdateTimestamp
    protected LocalDateTime edited;

    public Reservation(LocalDate bookingDate,Member member,Movie movie){
        this.bookingDate = bookingDate;
        this.member = member;
        member.addReservation(this);
        movie.addReservation(this);
    }

}

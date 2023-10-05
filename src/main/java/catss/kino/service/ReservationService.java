package catss.kino.service;

import catss.kino.dto.ReservationRequest;
import catss.kino.dto.ReservationResponse;
import catss.kino.entity.Member;
import catss.kino.entity.Movie;
import catss.kino.entity.Reservation;
import catss.kino.repository.MemberRepository;
import catss.kino.repository.MovieRepository;
import catss.kino.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;
    MovieRepository movieRepository;

    public ReservationService(MovieRepository movieRepository,MemberRepository memberRepository,ReservationRepository reservationRepository){
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.movieRepository = movieRepository;
    }

    public ReservationResponse reserveTicket(ReservationRequest body){
        if (body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
        }
        Member member = memberRepository.findById(body.getUserName()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Movie movie = movieRepository.findById(body.getMovieId()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Reservation res = reservationRepository.save(new Reservation(body.getDate(),member,movie, body.getSeats()));
        return new ReservationResponse(res);
    }
    public List<ReservationResponse> getReservationsForUser(String username){
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        List<ReservationResponse> reservations = member.getReservations().stream().map(r -> new ReservationResponse(r)).toList();
        return reservations;
    }
}

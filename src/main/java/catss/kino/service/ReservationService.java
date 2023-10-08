package catss.kino.service;

import catss.kino.dto.ReservationRequest;
import catss.kino.dto.ReservationResponse;
import catss.kino.entity.Member;
import catss.kino.entity.Movie;
import catss.kino.entity.Reservation;
import catss.kino.entity.Showing;
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
    ReservationRepository reservationRepository;
    MemberService memberService;
    ShowingService showingService;

    public ReservationService(ShowingService showingService,MemberService memberService,ReservationRepository reservationRepository){
        this.memberService = memberService;
        this.reservationRepository = reservationRepository;
        this.showingService = showingService;
    }

    public ReservationResponse reserveTicket(ReservationRequest body){
        if (body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
        }
        Member member = memberService.getMemberByUsername(body.getUserName());
        Showing showing = showingService.getShowingById(body.getShowingId());
        Reservation res = reservationRepository.save(new Reservation(body.getDate(),member,showing, body.getSeats()));
        return new ReservationResponse(res);
    }
    public List<ReservationResponse> getReservationsForUser(String username){
        Member member = memberService.getMemberByUsername(username);
        List<ReservationResponse> reservations = member.getReservations().stream().map(r -> new ReservationResponse(r)).toList();
        return reservations;
    }
}

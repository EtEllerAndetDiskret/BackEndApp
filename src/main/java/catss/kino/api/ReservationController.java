package catss.kino.api;

import catss.kino.dto.ReservationRequest;
import catss.kino.dto.ReservationResponse;
import catss.kino.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }
    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest res){
        ReservationResponse r = service.reserveTicket(res);
        return r;
    }
    @GetMapping("/{username}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String username){
        List<ReservationResponse> res = service.getReservationsForUser(username);
        return res;
    }
}

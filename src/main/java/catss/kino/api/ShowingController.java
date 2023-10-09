package catss.kino.api;

import catss.kino.dto.ShowingRequest;
import catss.kino.dto.ShowingResponse;
import catss.kino.entity.Showing;
import catss.kino.service.ShowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showings")
public class ShowingController {

    ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping
    List<ShowingResponse> getAllShowings(){
        return showingService.getAll();
    }

    @PostMapping
    ShowingResponse addShowing(@RequestBody ShowingRequest request){
        return showingService.addShowing(request);
    }

    @GetMapping("/{showingId}")
    ShowingResponse getShowingById(@PathVariable int showingId){
        return new ShowingResponse(showingService.getShowingById(showingId));
    }

    @GetMapping("/movie/{movieId}")
    List<ShowingResponse> getShowingsByMovieId(@PathVariable String movieId){
        return showingService.getShowingsOfMovie(movieId);
    }

}

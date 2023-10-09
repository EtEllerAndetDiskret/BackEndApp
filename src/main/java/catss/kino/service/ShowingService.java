package catss.kino.service;

import catss.kino.dto.ShowingRequest;
import catss.kino.dto.ShowingResponse;
import catss.kino.entity.Movie;
import catss.kino.entity.Showing;
import catss.kino.repository.ShowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowingService {
    ShowingRepository showingRepository;
    MovieService movieService;

    public ShowingService(ShowingRepository showingRepository, MovieService movieService) {
        this.showingRepository = showingRepository;
        this.movieService = movieService;
    }

    public Showing getShowingById(int showingId) {
        return showingRepository.findById(showingId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<ShowingResponse> getAll() {
        return showingRepository.findAll().stream().map(
                ShowingResponse::new
        ).collect(Collectors.toList());
    }

    public ShowingResponse addShowing(ShowingRequest request) {
        Movie movie = movieService.getMovieById(request.getMovieId());
        Showing showing = new Showing(movie, request.getHallId(), request.getStart(), request.getDurationInMinutes(), request.getPrice());
        return new ShowingResponse(showingRepository.save(showing));
    }

    public List<ShowingResponse> getShowingsOfMovie(int movieId) {
        return showingRepository.findAllByMovieId(movieId).stream().map(ShowingResponse::new).collect(Collectors.toList());
    }
}

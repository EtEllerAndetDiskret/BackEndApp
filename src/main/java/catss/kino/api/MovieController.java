package catss.kino.api;


import catss.kino.dto.MovieOmdbResponse;
import catss.kino.entity.Movie;
import catss.kino.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/imdbid/{imdbId}")
    public Movie getMovie(@PathVariable String imdbId) {
        return movieService.getMovieByImdbId(imdbId);
    }

    @PostMapping("/{imdbId}")
    public Movie addMovie(@PathVariable String imdbId) throws JsonProcessingException {
        return movieService.addMovie(imdbId);
    }

    @GetMapping
    List<Movie> getMovies(){
        return movieService.getAllMovies();
    }
}
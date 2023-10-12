package catss.kino.api;


import catss.kino.dto.MovieOmdbResponse;
import catss.kino.entity.Movie;
import catss.kino.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Get movie from specific ID
    @RequestMapping("/imdbid/{imdbId}")
    public Movie getMovie(@PathVariable String imdbId) {
        return movieService.getMovieByImdbID(imdbId);
    }

    //POST a movie
    @PostMapping("/{imdbId}")
    public Movie addMovie(@PathVariable String imdbId) throws JsonProcessingException {
        return movieService.addMovie(imdbId);
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity <String> deleteMovie(@PathVariable String imdbId) throws JsonProcessingException {
        movieService.deleteMovieByImdbID(imdbId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Get all movies
    @GetMapping
    List<Movie> getMovies(){
        return movieService.getAllMovies();
    }
}
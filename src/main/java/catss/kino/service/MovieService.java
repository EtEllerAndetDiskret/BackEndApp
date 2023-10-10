package catss.kino.service;


import catss.kino.api_facade.AzureTranslate;
import catss.kino.api_facade.OmdbFacade;
import catss.kino.dto.MovieOmdbResponse;
import catss.kino.entity.Movie;
import catss.kino.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    AzureTranslate translator;

    @Autowired
    OmdbFacade omdbFacade;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbID(imdbId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }


    public Movie addMovie(String imdbId) throws JsonProcessingException {
        // Builds the URL and gets the movie
        MovieOmdbResponse dto = omdbFacade.getMovie(imdbId);
        // Translate the plot to danish
        String dkPlot = translator.translate(dto.getPlot());

        // Build movie entity
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .rated(dto.getRated())
                .released(dto.getReleased())
                .runtime(dto.getRuntime())
                .genre(dto.getGenre())
                .director(dto.getDirector())
                .writer(dto.getWriter())
                .actors(dto.getActors())
                .metascore(dto.getMetascore())
                .imdbRating(dto.getImdbRating())
                .imdbVotes(dto.getImdbVotes())
                .website(dto.getWebsite())
                .response(dto.getResponse())
                .plot(dto.getPlot())
                .plotDK(dkPlot)
                .poster(dto.getPoster())
                .imdbID(dto.getImdbID())
                .build();
        try {
            // Save movie to our repository
            movieRepository.save(movie);
            return movie;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add movie");
        }
    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    public Movie getMovieById(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Boolean> deleteMovie(int imdbId) {
        if(!movieRepository.existsById(imdbId)) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this ID doesn't exist");
        }
        try {
            movieRepository.deleteById(imdbId);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete movie - for some reason");
        }
    }
}
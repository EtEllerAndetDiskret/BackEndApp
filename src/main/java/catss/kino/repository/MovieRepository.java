package catss.kino.repository;

import catss.kino.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.FetchMode.SELECT;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //  boolean findByImdbID(String imdbId);
    Optional<Movie> findMovieByImdbId(String imdbId);
    Optional<Movie> deleteMovieByImdbId(String imdbId);

}



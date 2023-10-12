package catss.kino.repository;

import catss.kino.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.FetchMode.SELECT;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //  boolean findByImdbID(String imdbId);


    @Query("SELECT m FROM Movie m WHERE m.imdbID = :imdbId ")
    Optional<Movie> findMovieByImdbID(String imdbId);

    @Query("DELETE FROM Movie m WHERE m.imdbID = :imdbId ")
    Optional<Movie> deleteMovieByImdbID(String imdbId);

}



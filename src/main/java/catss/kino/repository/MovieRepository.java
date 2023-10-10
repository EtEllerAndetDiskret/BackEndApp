package catss.kino.repository;

import catss.kino.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    boolean findByImdbID(String imdbId);
}
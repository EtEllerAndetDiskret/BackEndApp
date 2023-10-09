package catss.kino.repository;

import catss.kino.dto.ShowingResponse;
import catss.kino.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findAllByMovieId(int movieId);

    @Query(
            value = "SELECT hall_id, s.id, movie_id, price, end, start FROM showing s INNER JOIN movies m ON s.movie_id = m.id WHERE imdbid = \"tt0088247\";",
            nativeQuery = true
    )
    List<Showing> findAllByMovieImdbId(String movieId);
}

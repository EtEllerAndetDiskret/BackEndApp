package catss.kino.repository;

import catss.kino.dto.ShowingResponse;
import catss.kino.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findAllByMovieId(int movieId);
}

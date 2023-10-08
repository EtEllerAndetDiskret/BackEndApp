package catss.kino.repository;

import catss.kino.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}

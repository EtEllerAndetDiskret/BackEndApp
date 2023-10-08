package catss.kino.service;

import catss.kino.entity.Showing;
import catss.kino.repository.ShowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShowingService {
    ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public Showing getShowingById(int showingId) {
        return showingRepository.findById(showingId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

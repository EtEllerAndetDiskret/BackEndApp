package catss.kino.config;

import catss.kino.entity.Member;
import catss.kino.entity.Reservation;
import catss.kino.entity.Showing;
import catss.kino.repository.MemberRepository;
import catss.kino.repository.ShowingRepository;
import catss.kino.service.MovieService;
import catss.security.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    final MemberRepository memberRepository;
    final MovieService movieService;
    final ShowingRepository showingRepository;


    public DeveloperData(MemberRepository memberRepository, MovieService movieService, ShowingRepository showingRepository) {
        this.memberRepository = memberRepository;
        this.movieService = movieService;
        this.showingRepository = showingRepository;
    }

    @Override
    public void run(ApplicationArguments args){
        SetupDummyMembers();
        SetupDummyMovies();
        SetupDummyShowings();

    }

    private void SetupDummyShowings() {
        List<Showing> dummyShowings = new ArrayList<>();
        LocalDateTime baseTime = LocalDateTime.of(2023, 10, 12, 9, 0);

        List<Reservation> reservations = new ArrayList<>();
        Showing showing7 = new Showing(movieService.getMovieById(7), 2, baseTime.plusDays(6), 300L, 99);
        Showing showing1 = new Showing(movieService.getMovieById(1), 1, baseTime.plusDays(1), 240L, 120);
        Showing showing2 = new Showing(movieService.getMovieById(2), 2, baseTime.plusDays(2), 300L, 180);
        Showing showing3 = new Showing(movieService.getMovieById(3), 2, baseTime.plusDays(1), 180L, 200);
        Showing showing4 = new Showing(movieService.getMovieById(4), 1, baseTime.plusDays(4), 240L, 99);
        Showing showing5 = new Showing(movieService.getMovieById(5), 2, baseTime.plusDays(3), 180L, 69.95);
        Showing showing6 = new Showing(movieService.getMovieById(6), 1, baseTime.plusDays(1), 240L, 120);

        showingRepository.saveAll(new ArrayList<>(List.of(showing1, showing2, showing3, showing4, showing5, showing6, showing7)));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                showingRepository.save(new Showing(movieService.getMovieById(2), i%2+1, baseTime.plusDays(i).plusHours(j*4), 240L, 120));
            }
        }
    }

    private void SetupDummyMovies() {
        try {
            movieService.addMovie("tt0088247");
            movieService.addMovie("tt21807222");
            movieService.addMovie("tt0033467");
            movieService.addMovie("tt9224104");
            movieService.addMovie("tt1517268");
            movieService.addMovie("tt9362722");
            movieService.addMovie("tt6791350");
            movieService.addMovie("tt6718170");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void SetupDummyMembers() {
        String passwordUsedByAll = "1234";
        Member member1 = new Member(
                "user1",
                passwordUsedByAll,
                "email1",
                "firstname1",
                "lastName1",
                "street1",
                "city1",
                "zip1"
        );
        Member member2 = new Member(
                "user2",
                passwordUsedByAll,
                "email2",
                "firstname2",
                "lastName2",
                "street2",
                "city2",
                "zip2"
        );
        Member member3 = new Member(
                "user3",
                passwordUsedByAll,
                "email3",
                "firstname3",
                "lastName3",
                "street3",
                "city3",
                "zip3"
        );
        Member member4 = new Member(
                "user4",
                passwordUsedByAll,
                "email4",
                "firstname4",
                "lastName4",
                "street4",
                "city4",
                "zip4"
        );

        member1.addRole(Role.ADMIN);
        member2.addRole(Role.USER);
        member3.addRole(Role.ADMIN);
        member3.addRole(Role.USER);
        ArrayList<Member> members = new ArrayList<>( List.of(member1, member2, member3, member4));
        memberRepository.saveAll(members);
    }
}

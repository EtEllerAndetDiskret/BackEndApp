package catss.kino.config;

import catss.kino.entity.Member;
import catss.kino.repository.MemberRepository;
import catss.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    final MemberRepository memberRepository;

    public DeveloperData(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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

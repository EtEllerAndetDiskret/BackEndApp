package catss.kino.entity;


import catss.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {

    private String firstName;
    private String lastName;
    private String street;
    private String zip;
    private String city;

    @OneToMany(mappedBy = "member")
    List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if (reservation == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

    public Member(String user, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
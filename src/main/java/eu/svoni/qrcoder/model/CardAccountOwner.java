package eu.svoni.qrcoder.model;

import java.util.List;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_account_owner")
public class CardAccountOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    @OneToMany
    private List<QrCard> cards;

}

package eu.svoni.qrcoder.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qrcard")
public class QrCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String value;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private String name;

    @OneToMany
    private List<QrCardAttribute> attributes;

    @ManyToOne
    private CardAccountOwner cardAccountOwner;

    @ManyToOne
    private CardUser cardUser;

}
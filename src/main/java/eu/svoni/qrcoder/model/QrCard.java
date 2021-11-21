package eu.svoni.qrcoder.model;

import java.time.LocalDateTime;
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

}
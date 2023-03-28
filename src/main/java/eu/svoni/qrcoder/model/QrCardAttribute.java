package eu.svoni.qrcoder.model;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qrcard_attribute")
public class QrCardAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String valueS;
    private LocalDateTime valueD;
    private Long valueN;
    private Boolean valueB;

    @ManyToOne
    private QrCard qrCard;

}

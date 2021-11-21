package eu.svoni.qrcoder.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrCardDto {

    private UUID uuid;
    private String value;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private String name;

}

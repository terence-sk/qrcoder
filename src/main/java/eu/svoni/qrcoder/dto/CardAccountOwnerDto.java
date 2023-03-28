package eu.svoni.qrcoder.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardAccountOwnerDto {
    private String firstName;
    private String lastName;
    private String companyName;
    private String password;
    private String matchingPassword;
    private String email;
}

package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    @Size(min = 2)
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "[+]")
    @Size(min = 11, max = 13)
    private String phoneNumber;
}
// END

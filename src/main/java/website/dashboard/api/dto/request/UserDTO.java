package website.dashboard.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.dashboard.api.enums.Authorities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long id;

    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 3, max = 100)
    private String name;

    @NotEmpty(message = "The username cannot be empty")
    @Size(min = 3, max = 100)
    private String userName;

    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 8, max = 100)
    private String password;


   @Enumerated(EnumType.STRING)
   @NotEmpty(message = "The authorities cannot be empty")
    private Authorities authorities;
}

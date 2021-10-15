package website.dashboard.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long id;

    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 3, max = 100)
    @Schema(description = "The name of user", example = "José")
    private String name;

    @NotEmpty(message = "The username cannot be empty")
    @Schema(description = "The username", example = "José-890")
    @Size(min = 3, max = 100)
    private String username;

    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 8, max = 100)
    @Schema(description = "The password", example = "@Jose112255")
    private String password;

    @Pattern(regexp = "ROLE_ADMIN|ROLE_USER|ROLE_ADMIN,ROLE_USER")
    @Schema(description = "The authorities of user", example = "ROLE_ADMIN")
    private String authorities;



}

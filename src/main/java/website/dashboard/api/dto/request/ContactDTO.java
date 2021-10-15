package website.dashboard.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {

    private long id;

    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "Please provide a valid email address")
    @Schema(description = "Here is your company email", example = "contact@yourcompany.com")
    private String email;

    @Size(min = 13, max = 14)
    @NotEmpty(message = "The phone cannot be empty")
    @Schema(description = "Here is your company phone", example = "(11)111155555")
    private String phone;
}

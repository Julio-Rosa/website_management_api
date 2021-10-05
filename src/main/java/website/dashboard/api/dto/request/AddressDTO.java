package website.dashboard.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private long id;

    @NotEmpty(message = "The city cannot be empty")
    private String city;
    @NotEmpty(message = "The district cannot be empty")
    private String district;
    @NotEmpty(message = "The street cannot be empty")
    private String street;
    @NotEmpty(message = "The number cannot be empty")
    private String number;
    private String complement;
}

package website.dashboard.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "This is a Name of city", example = "São Paulo -SP")
    private String city;
    @NotEmpty(message = "The district cannot be empty")
    @Schema(description = "This is a Name of district", example = "Bela Vista")
    private String district;
    @NotEmpty(message = "The street cannot be empty")
    @Schema(description = "This is a Name of street", example = "Rua Treze de Maio")
    private String street;
    @NotEmpty(message = "The number cannot be empty")
    @Schema(description = "This is a number of your address", example = "25")
    private String number;
    @Schema(description = "A brief description that can help you find your address", example = "any description")
    private String complement;
}

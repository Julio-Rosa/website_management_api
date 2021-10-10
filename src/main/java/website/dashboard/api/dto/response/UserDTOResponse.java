package website.dashboard.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {


    private long id;
    private String name;
    private String username;

    private String authorities;



}

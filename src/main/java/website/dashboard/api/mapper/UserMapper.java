package website.dashboard.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import website.dashboard.api.dto.request.ContactDTO;
import website.dashboard.api.dto.request.UserDTO;
import website.dashboard.api.dto.response.UserDTOResponse;
import website.dashboard.api.model.Contact;
import website.dashboard.api.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
    UserDTOResponse toDTOResponse(User user);

}

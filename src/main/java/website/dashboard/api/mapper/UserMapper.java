package website.dashboard.api.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.dto.request.UserDTO;
import website.dashboard.api.dto.response.UserDTOResponse;
import website.dashboard.api.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO);

    //@Mapping(target = "authorities", ignore = true)
    @Mapping(target = "authorities", expression = "java(user.getAuthorities().toString())")
    UserDTO toDTO(User user);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().toString())")
    UserDTOResponse toDTOResponse(User user);


}
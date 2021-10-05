package website.dashboard.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.model.Address;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);
}

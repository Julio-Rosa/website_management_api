package website.dashboard.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import website.dashboard.api.dto.request.ContactDTO;
import website.dashboard.api.model.Contact;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact toModel(ContactDTO contactDTO);

    ContactDTO toDTO(Contact contact);
}

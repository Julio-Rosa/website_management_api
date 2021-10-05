package website.dashboard.api.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import website.dashboard.api.dto.request.ContactDTO;
import website.dashboard.api.mapper.ContactMapper;
import website.dashboard.api.model.Contact;
import website.dashboard.api.repository.ContactRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {
    private ContactRepository contactRepository;

    private final ContactMapper contactMapper = ContactMapper.INSTANCE;

    public Contact createContact(ContactDTO contactDTO){
        Contact contactToSave = contactMapper.toModel(contactDTO);
        return contactRepository.save(contactToSave);
    }
    public List<Contact> listAll(){
        return contactRepository.findAll();
    }
    public Contact findById(long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact Not Found"));

    }
    public void delete(Long id){
        findById(id);
        contactRepository.deleteById(id);
    }
    public Contact updateById(long id, ContactDTO contactDTO){
        findById(id);
        Contact contactToUpdate = contactMapper.toModel(contactDTO);
        return contactRepository.save(contactToUpdate);
    }
}

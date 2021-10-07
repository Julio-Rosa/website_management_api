package website.dashboard.api.service;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.mapper.AddressMapper;
import website.dashboard.api.model.Address;
import website.dashboard.api.repository.AddressRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public Address createAddress(AddressDTO addressDTO){
        Address addressToSave = addressMapper.toModel(addressDTO);
        return addressRepository.save(addressToSave);
    }
    public List<Address> listAll(){
        return addressRepository.findAll();
    }
    public Address findById(long id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Address Not Found"));

    }
    public void delete(long id){
        findById(id);
        addressRepository.deleteById(id);
    }
    public Address update(AddressDTO addressDTO){
        findById(addressDTO.getId());
        Address addressToUpdate = addressMapper.toModel(addressDTO);
        return addressRepository.save(addressToUpdate);
    }

}

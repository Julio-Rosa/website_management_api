package website.dashboard.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.model.Address;
import website.dashboard.api.service.AddressService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/address")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDTO addressDTO){
        return new ResponseEntity(addressService.createAddress(addressDTO), HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Address>> listAll(){
        return new ResponseEntity<>(addressService.listAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable long id){
        return new ResponseEntity<>(addressService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id){
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Address> update(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.update(addressDTO), HttpStatus.OK);
    }


}

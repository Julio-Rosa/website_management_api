package website.dashboard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When address is successfully created")

    })
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDTO addressDTO){
        return new ResponseEntity(addressService.createAddress(addressDTO), HttpStatus.CREATED);

    }
    @GetMapping
    @Operation(summary = "List all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation")

    })
    public ResponseEntity<List<Address>> listAll(){
        return new ResponseEntity<>(addressService.listAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    @Operation(summary = "Return address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When address with this id not exits")

    })
    public ResponseEntity<Address> findById(@PathVariable long id){
        return new ResponseEntity<>(addressService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "When the address is successfully deleted"),
            @ApiResponse(responseCode = "404", description = "When address with this id not exits")

    })
    public ResponseEntity<Void> delete(@PathVariable long id){
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When the address is successfully updated"),
            @ApiResponse(responseCode = "404", description = "When address  not exits")

    })
    public ResponseEntity<Address> update(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.update(addressDTO), HttpStatus.OK);
    }


}
